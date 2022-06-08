package com.codepath.apps.restclienttemplate.models;

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

    public Tweet() {
    }

    public static Tweet extractFromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.mBody = jsonObject.getString("text");
        tweet.mCreatedAt = jsonObject.getString("created_at");
        tweet.mUser = User.extractFromJson(jsonObject.getJSONObject("user"));
        return tweet;
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
