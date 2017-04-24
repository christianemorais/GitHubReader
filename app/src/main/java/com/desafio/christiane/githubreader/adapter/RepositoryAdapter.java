package com.desafio.christiane.githubreader.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desafio.christiane.githubreader.BR;
import com.desafio.christiane.githubreader.databinding.ItemRepositoryBinding;
import com.desafio.christiane.githubreader.model.RepositoryItem;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RepositoryItem> repositoryItems;
    private View.OnClickListener clickListener = null;

    public RepositoryAdapter(View.OnClickListener clickListener, List<RepositoryItem> repositoryItems) {
        this.clickListener = clickListener;
        this.repositoryItems = repositoryItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        ItemRepositoryBinding itemBinding = ItemRepositoryBinding.inflate(layoutInflater, parent, false);
        itemBinding.cvRepository.setOnClickListener(clickListener);

        return new RepositoryViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RepositoryViewHolder viewHolder = (RepositoryViewHolder) holder;

        viewHolder.bind(repositoryItems.get(position));
    }

    @Override
    public int getItemCount() {
        return repositoryItems.size();
    }

    private static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        RepositoryViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(RepositoryItem item) {
            binding.setVariable(BR.repositoryItem, item);
            binding.executePendingBindings();
        }
    }
}
