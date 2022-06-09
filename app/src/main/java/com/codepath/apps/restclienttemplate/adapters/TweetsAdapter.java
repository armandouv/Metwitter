package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    private final Context mContext;
    private final List<Tweet> mTweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.mContext = context;
        this.mTweets = tweets;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = mTweets.get(position);
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mProfileImage;
        private final TextView mBodyView;
        private final TextView mScreenNameView;
        private final ImageView mEmbeddedImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mProfileImage = itemView.findViewById(R.id.profile_image);
            mBodyView = itemView.findViewById(R.id.text_body);
            mScreenNameView = itemView.findViewById(R.id.screen_name);
            mEmbeddedImage = itemView.findViewById(R.id.embedded);
        }

        public void bind(Tweet tweet) {
            mBodyView.setText(tweet.getBody());
            mScreenNameView.setText(tweet.getUser().getScreenName());

            Glide.with(mContext)
                    .load(tweet.getUser().getProfileImageUrl())
                    .into(mProfileImage);

            if (tweet.getEmbeddedImageUrl() == null) {
                mEmbeddedImage.setVisibility(View.GONE);
                return;
            }

            Glide.with(mContext)
                    .load(tweet.getEmbeddedImageUrl())
                    .into(mEmbeddedImage);
        }
    }

    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }
}
