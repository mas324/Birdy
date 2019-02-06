package com.codepath.apps.birdy.models;

import android.text.format.DateUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Tweet {

    private String tweet;
    private Long uid;
    private String createdTime;
    private User user;

    public Tweet() {
        tweet = "";
        uid = Long.MIN_VALUE;
        createdTime = "";
        user = new User();
    }

    private Tweet(String tweet, Long uid, String createdTime, User user) {
        this.tweet = tweet;
        this.uid = uid;
        this.createdTime = createdTime;
        this.user = user;
    }

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        return new Tweet(jsonObject.getString("text"), jsonObject.getLong("id"), jsonObject.getString("created_at"), User.fromJSON(jsonObject.getJSONObject("user")));
    }

    public String getTweet() {
        return tweet;
    }

    public Long getUid() {
        return uid;
    }

    public String getCreatedTime() {
        String dateFormat = "EEE MMM DD HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        format.setLenient(true);
        String timeOffset = "";
        try {
            Log.d("BirdyDate", createdTime);
            Log.d("BirdyDate", Long.toString(format.parse(createdTime).getTime()));
            Log.d("BirdyDate", Long.toString(System.currentTimeMillis()));
            timeOffset = DateUtils.getRelativeTimeSpanString(format.parse(createdTime).getTime(), System.currentTimeMillis(), DateUtils.DAY_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return timeOffset;
    }

    public User getUser() {
        return user;
    }
}
