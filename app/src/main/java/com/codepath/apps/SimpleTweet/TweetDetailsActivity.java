package com.codepath.apps.SimpleTweet;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.apps.SimpleTweet.databinding.ActivityTweetDetailsBinding;
import com.codepath.apps.SimpleTweet.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {
    private ActivityTweetDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTweetDetailsBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        Glide.with(this)
                .load(tweet.getUser().getProfileImageUrl())
                .into(mBinding.profileImageDetails);
        mBinding.textBodyDetails.setText(tweet.getBody());
        mBinding.nameDetails.setText(tweet.getUser().getName());

        if (tweet.getEmbeddedImageUrl() == null) {
            mBinding.embeddedDetails.setVisibility(View.GONE);
        } else {
            Glide.with(this)
                    .load(tweet.getEmbeddedImageUrl())
                    .into(mBinding.embeddedDetails);
        }

        mBinding.createdAt.setText(tweet.getCreatedAt());
        mBinding.likes.setText(String.format("%d likes", tweet.getLikes()));
        mBinding.retweets.setText(String.format("%d retweets", tweet.getRetweets()));
        mBinding.username.setText(tweet.getUser().getScreenName());
    }
}