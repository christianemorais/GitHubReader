package com.desafio.christiane.githubreader.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desafio.christiane.githubreader.Api;
import com.desafio.christiane.githubreader.R;
import com.desafio.christiane.githubreader.adapter.PullRequestAdapter;
import com.desafio.christiane.githubreader.databinding.FragmentPullRequestListBinding;
import com.desafio.christiane.githubreader.model.PullRequest;
import com.desafio.christiane.githubreader.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestListFragment extends Fragment implements Callback<List<PullRequest>> {
    private FragmentPullRequestListBinding binding;

    private List<PullRequest> pullRequests = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Util.openDialog(getContext(), R.string.loading, R.string.wait_loading);

        binding = DataBindingUtil.inflate(inflater, R.layout
                .fragment_pull_request_list, container, false);

        if (getArguments() != null) {
            Api.loadPullRequestList(
                    getArguments().getString(Util.SELECTED_CREATOR),
                    getArguments().getString(Util.SELECTED_REPOSITORY), this);
        } else {
            showEmptyMessage();
        }

        Util.closeDialog();

        return binding.getRoot();
    }

    private void showEmptyMessage() {
        binding.empty.rlEmpty.setVisibility(View.VISIBLE);
    }

    private void configureRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        final PullRequestAdapter adapter =
                new PullRequestAdapter(new ClickItem(layoutManager), pullRequests);

        binding.rvPullRequest.setLayoutManager(layoutManager);
        binding.rvPullRequest.setAdapter(adapter);
    }

    @Override
    public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
        pullRequests.addAll(response.body());

        if (!pullRequests.isEmpty()) {
            configureRecyclerView();
        } else {
            showEmptyMessage();
        }
    }

    @Override
    public void onFailure(Call<List<PullRequest>> call, Throwable throwable) {
        showEmptyMessage();
    }

    /**
     * Classe para tratar o evento de clique no item do recyclerView
     */
    private class ClickItem implements View.OnClickListener {
        private RecyclerView.LayoutManager layoutManager;

        ClickItem(RecyclerView.LayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(pullRequests.get(layoutManager.getPosition(v)).getHtmlUrl()));

            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
}
