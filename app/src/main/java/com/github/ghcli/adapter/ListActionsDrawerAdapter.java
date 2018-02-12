package com.github.ghcli.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.viewholder.ActionDrawerViewHolder;


public class ListActionsDrawerAdapter extends RecyclerView.Adapter {

    private Context context;
    private String[] actions = {"profile", "repositories", "followers", "logout"};

    public ListActionsDrawerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.actions_list_drawer, parent, false);

        ActionDrawerViewHolder viewHolder = new ActionDrawerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ActionDrawerViewHolder viewHolder = (ActionDrawerViewHolder) holder;
        String action = actions[position];
        viewHolder.setActionText(action);
    }

    @Override
    public int getItemCount() {
        return actions.length;
    }
}
