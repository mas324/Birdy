package com.codepath.apps.birdy.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String name;
    private Long id;
    private String handle;
    private String profilePic;

    public User() {
        name = "";
        id = Long.MIN_VALUE;
        handle = "";
        profilePic = "";
    }

    public User(String name, Long id, String handle, String profilePic) {
        this.name = name;
        this.id = id;
        this.handle = handle;
        this.profilePic = profilePic;
    }

    public static User fromJSON(JSONObject user) throws JSONException {
        return new User(user.getString("name"), user.getLong("id"), user.getString("screen_name"), user.getString("profile_image_url"));
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getHandle() {
        return "@" + handle;
    }

    public String getProfilePic() {
        return profilePic;
    }
}
