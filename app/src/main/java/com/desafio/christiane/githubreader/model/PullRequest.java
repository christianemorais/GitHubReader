package com.desafio.christiane.githubreader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chris.morais on 16/07/16
 */
public class PullRequest {

    private static final String CREATED_AT = "Criado em %s às %s";
    
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("user")
    private User user;
    @SerializedName("body")
    private String body;
    @SerializedName("created_at")
    private String createdAt;

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        if (!createdAt.isEmpty()) {

            String day = createdAt.substring(0, 10);
            String time = createdAt.substring(11, 19);

            return String.format(CREATED_AT, day, time);
        }

        return createdAt;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
