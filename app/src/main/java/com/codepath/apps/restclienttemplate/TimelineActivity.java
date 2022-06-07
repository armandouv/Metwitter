package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.apps.restclienttemplate.adapters.TweetsAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;


public class TimelineActivity extends AppCompatActivity {
    private TwitterClient mClient;
    public final static String TAG = "TimelineActivity";
    private RecyclerView mTweetsView;
    private List<Tweet> mTweets;
    private TweetsAdapter mTweetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        mClient = TwitterApp.getRestClient(this);
        mTweetsView = findViewById(R.id.tweets_view);
        mTweets = new ArrayList<>();
        mTweetsAdapter = new TweetsAdapter(this, mTweets);

        mTweetsView.setLayoutManager(new LinearLayoutManager(this));
        mTweetsView.setAdapter(mTweetsAdapter);
        populateHomeTimeline();

        this.mClient = TwitterApp.getRestClient(this);
        populateHomeTimeline();
    }

    private void populateHomeTimeline() {
        mClient.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONArray jsonArray = json.jsonArray;
                try {
                    List<Tweet> tweets = Tweet.extractFromJsonArray(jsonArray);
                    mTweets.addAll(tweets);
                    mTweetsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e(TAG, "JSON Exception", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.e(TAG, "onFailure: " + response, throwable);
            }
        });
    }
}