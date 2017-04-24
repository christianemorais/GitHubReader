package com.desafio.christiane.githubreader;

import com.desafio.christiane.githubreader.model.PullRequest;
import com.desafio.christiane.githubreader.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

class GitHubRequest {
    private static final String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit = null;

    private static GitHubService service;

    static GitHubService getService() {
        if (service == null) {
            service = getClient().create(GitHubService.class);
        }

        return service;
    }

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    interface GitHubService {
        @GET("search/repositories?q=language:Java&sort=stars")
        Call<Repository> getRepositories(@Query("page") int page);

        @GET("repos/{creator}/{repository}/pulls")
        Call<List<PullRequest>> getPullRequests(@Path("creator") String creator, @Path("repository")
                String repository);
    }
}
