package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ComposeActivity extends AppCompatActivity {
    private static final int MAX_TWEET_LENGTH = 280;
    private EditText mComposeText;
    private Button mSubmitTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

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

            Toast.makeText(ComposeActivity.this, tweetContent, Toast.LENGTH_LONG).show();
        });
    }
}