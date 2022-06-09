package com.codepath.apps.SimpleTweet.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {
    private String mName;
    private String mScreenName;
    private String mProfileImageUrl;

    public User() {
    }

    public String getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public String getScreenName() {
        return mScreenName;
    }

    public static User extractFromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.mName = jsonObject.getString("name");
        user.mScreenName = jsonObject.getString("screen_name");
        user.mProfileImageUrl = jsonObject.getString("profile_image_url_https");
        return user;
    }
}
