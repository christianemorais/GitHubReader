package com.desafio.christiane.githubreader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chris.morais on 16/07/16
 */
public class User {
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public User(String login, String avatarUrl) {
        setLogin(login);
        setAvatarUrl(avatarUrl);
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
