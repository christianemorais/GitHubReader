package com.desafio.christiane.githubreader.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desafio.christiane.githubreader.Api;
import com.desafio.christiane.githubreader.R;
import com.desafio.christiane.githubreader.adapter.RepositoryAdapter;
import com.desafio.christiane.githubreader.databinding.FragmentRepositoryListBinding;
import com.desafio.christiane.githubreader.model.Repository;
import com.desafio.christiane.githubreader.model.RepositoryItem;
import com.desafio.christiane.githubreader.util.EndlessRecyclerViewScrollListener;
import com.desafio.christiane.githubreader.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryListFragment extends Fragment implements Callback<Repository> {
    private FragmentRepositoryListBinding binding;
    private int currentPage = 1;
    private List<RepositoryItem> repositoryItems = new ArrayList<>();
    private RepositoryAdapter adapter;

    private RepositoryListFragment instance = this;

    public RepositoryListFragment getInstance() {
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Util.openDialog(getActivity(), R.string.loading, R.string.wait_loading);

        binding = DataBindingUtil.inflate(inflater, R.layout
                .fragment_repository_list, container, false);

        Api.loadRepositoryList(currentPage, this);

        return binding.getRoot();
    }

    private void configureRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        adapter = new RepositoryAdapter(new ClickItem(layoutManager), repositoryItems);

        binding.rvRepositories.setLayoutManager(layoutManager);
        binding.rvRepositories
                .addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        Api.loadRepositoryList(page, getInstance());

                        currentPage = page;
                    }
                });

        binding.rvRepositories.setAdapter(adapter);
    }

    private void initRecylerView(List<RepositoryItem> response) {
        repositoryItems.addAll(response);

        if (currentPage == 1) {
            configureRecyclerView();
            Util.closeDialog();
        } else {
            adapter.notifyItemRangeChanged(adapter.getItemCount(),
                    repositoryItems.size() - 1);
        }
    }

    private void showEmptyMessage() {
        binding.empty.rlEmpty.setVisibility(View.VISIBLE);
        Util.closeDialog();
    }

    @Override
    public void onResponse(Call<Repository> call, Response<Repository> response) {
        if (!response.body().getItems().isEmpty()) {
            initRecylerView(response.body().getItems());
        } else {
            showEmptyMessage();
        }
    }

    @Override
    public void onFailure(Call<Repository> call, Throwable throwable) {
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
            int pos = layoutManager.getPosition(v);

            Bundle bundle = new Bundle();
            bundle.putString(Util.SELECTED_REPOSITORY, repositoryItems.get(pos).getName());
            bundle.putString(Util.SELECTED_CREATOR,
                    repositoryItems.get(pos).getOwner().getLogin());

            PullRequestListFragment fragment = new PullRequestListFragment();
            fragment.setArguments(bundle);

            Util.replaceFragment(getFragmentManager(), fragment);
        }
    }
}