package com.desafio.christiane.githubreader.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris.morais on 16/07/16
 */
public class Repository {

    @SerializedName("items")
    private List<RepositoryItem> items = new ArrayList<>();

    public List<RepositoryItem> getItems() {
        return items;
    }

    public void setItems(List<RepositoryItem> items) {
        this.items = items;
    }
}