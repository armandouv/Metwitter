package com.codepath.apps.SimpleTweet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.apps.SimpleTweet.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {
    private static final int MAX_TWEET_LENGTH = 280;
    public final static String TAG = "ComposeActivity";
    private EditText mComposeText;
    private Button mSubmitTweet;
    private TwitterClient mClient;
    private MenuItem mProgressBarItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        mClient = TwitterApp.getRestClient(this);

        mComposeText = findViewById(R.id.compose_multiline_text);
        mSubmitTweet = findViewById(R.id.submit_tweet);

        mSubmitTweet.setOnClickListener((view) -> {
            String tweetContent = mComposeText.getText().toString();

            if (tweetContent.isEmpty()) {
                Toast.makeText(ComposeActivity.this, "Your tweet cannot be empty",
                        Toast.LENGTH_LONG).show();
                return;
            }

            if (tweetContent.length() > MAX_TWEET_LENGTH) {
                Toast.makeText(ComposeActivity.this,
                        "Your tweet must be at most " + MAX_TWEET_LENGTH + " characters",
                        Toast.LENGTH_LONG).show();
                return;
            }

            mProgressBarItem.setVisible(true);
            mClient.publishTweet(tweetContent, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Headers headers, JSON json) {
                    try {
                        mProgressBarItem.setVisible(false);
                        Tweet tweet = Tweet.extractFromJson(json.jsonObject);
                        Intent intent = new Intent();
                        intent.putExtra("tweet", Parcels.wrap(tweet));
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (JSONException e) {
                        Log.e(TAG, "Failure to extract tweet from JSON", e);
                    }
                }

                @Override
                public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                    Log.e(TAG, "Failure to publish tweet", throwable);
                }
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        this.mProgressBarItem = menu.findItem(R.id.item_progress_bar);
        this.mProgressBarItem.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }
}