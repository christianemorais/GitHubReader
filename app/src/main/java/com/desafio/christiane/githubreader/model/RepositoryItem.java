package com.desafio.christiane.githubreader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chris.morais on 16/07/16
 */
public class RepositoryItem {
    @SerializedName("name")
    private String name;
    @SerializedName("owner")
    private User owner;
    @SerializedName("description")
    private String description;
    @SerializedName("stargazers_count")
    private int stargazersCount;
    @SerializedName("forks_count")
    private int forksCount;

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }
}
