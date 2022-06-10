package com.codepath.apps.SimpleTweet.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {
    private String mBody;
    private String mCreatedAt;
    private User mUser;
    private String mEmbeddedImageUrl;
    private int likes;
    private int retweets;

    public static Tweet extractFromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.mBody = jsonObject.has("full_text") ?
                jsonObject.getString("full_text") :
                jsonObject.getString("text");
        tweet.mCreatedAt = jsonObject.getString("created_at");
        tweet.mUser = User.extractFromJson(jsonObject.getJSONObject("user"));
        tweet.likes = jsonObject.getInt("favorite_count");
        tweet.retweets = jsonObject.getInt("retweet_count");

        JSONObject entities = jsonObject.getJSONObject("entities");
        if (!entities.has("media")) return tweet;

        JSONArray media = entities.getJSONArray("media");
        if (media.length() > 0)
            tweet.mEmbeddedImageUrl = media.getJSONObject(0).getString("media_url_https");

        return tweet;
    }

    public int getLikes() {
        return likes;
    }

    public int getRetweets() {
        return retweets;
    }

    public Tweet() {
    }

    public String getEmbeddedImageUrl() {
        return mEmbeddedImageUrl;
    }

    public String getBody() {
        return mBody;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public User getUser() {
        return mUser;
    }

    public static List<Tweet> extractFromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Tweet extractedTweet = extractFromJson(jsonObject);
            tweets.add(extractedTweet);
        }
        return tweets;
    }
}
