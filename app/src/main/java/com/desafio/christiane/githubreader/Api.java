package com.desafio.christiane.githubreader;

import com.desafio.christiane.githubreader.model.PullRequest;
import com.desafio.christiane.githubreader.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Api {

    private Api() {
    }

    public static void loadRepositoryList(int page, Callback<Repository> currentFragment) {
        Call<Repository> call = GitHubRequest.getService().getRepositories(page);

        call.enqueue(currentFragment);
    }

    public static void loadPullRequestList(String creator, String repository,
                                           Callback<List<PullRequest>> currentFragment) {
        Call<List<PullRequest>> call =
                GitHubRequest.getService().getPullRequests(creator, repository);

        call.enqueue(currentFragment);
    }
}