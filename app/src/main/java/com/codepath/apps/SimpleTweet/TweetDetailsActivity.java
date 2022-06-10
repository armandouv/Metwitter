package com.codepath.apps.SimpleTweet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.apps.SimpleTweet.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {
    private ImageView mProfileImageView;
    private TextView mBodyView;
    private TextView mNameView;
    private ImageView mEmbeddedImageView;
    private TextView mCreatedAtView;
    private TextView mLikesView;
    private TextView mRetweetsView;
    private TextView mUsernameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        this.mProfileImageView = findViewById(R.id.profile_image_details);
        this.mBodyView = findViewById(R.id.text_body_details);
        this.mNameView = findViewById(R.id.name_details);
        this.mEmbeddedImageView = findViewById(R.id.embedded_details);
        this.mCreatedAtView = findViewById(R.id.created_at);
        this.mLikesView = findViewById(R.id.likes);
        this.mRetweetsView = findViewById(R.id.retweets);
        this.mUsernameView = findViewById(R.id.username);

        Glide.with(this)
                .load(tweet.getUser().getProfileImageUrl())
                .into(mProfileImageView);
        this.mBodyView.setText(tweet.getBody());
        this.mNameView.setText(tweet.getUser().getName());

        if (tweet.getEmbeddedImageUrl() == null) {
            mEmbeddedImageView.setVisibility(View.GONE);
        } else {
            Glide.with(this)
                    .load(tweet.getEmbeddedImageUrl())
                    .into(mEmbeddedImageView);
        }

        this.mCreatedAtView.setText(tweet.getCreatedAt());
        this.mLikesView.setText(String.format("%d likes", tweet.getLikes()));
        this.mRetweetsView.setText(String.format("%d retweets", tweet.getRetweets()));
        this.mUsernameView.setText(tweet.getUser().getScreenName());
    }
}