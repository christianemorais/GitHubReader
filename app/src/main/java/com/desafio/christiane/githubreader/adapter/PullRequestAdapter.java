package com.desafio.christiane.githubreader.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desafio.christiane.githubreader.BR;
import com.desafio.christiane.githubreader.databinding.ItemPullRequestBinding;
import com.desafio.christiane.githubreader.model.PullRequest;

import java.util.List;

public class PullRequestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PullRequest> pullRequests;
    private View.OnClickListener clickListener = null;
    private Context context;

    public PullRequestAdapter(View.OnClickListener clickListener, List<PullRequest> pullRequests) {
        this.clickListener = clickListener;
        this.pullRequests = pullRequests;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        ItemPullRequestBinding itemBinding =
                ItemPullRequestBinding.inflate(layoutInflater, parent, false);
        itemBinding.cvPullRequest.setOnClickListener(clickListener);

        return new PullRequestViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PullRequestViewHolder viewHolder = (PullRequestViewHolder) holder;

        PullRequest item = pullRequests.get(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    private static class PullRequestViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        PullRequestViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PullRequest item) {
            binding.setVariable(BR.pullRequest, item);
            binding.executePendingBindings();
        }
    }
}
