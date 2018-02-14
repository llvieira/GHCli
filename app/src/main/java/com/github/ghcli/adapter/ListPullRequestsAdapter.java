package com.github.ghcli.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.models.GitHubPullRequest;
import com.github.ghcli.viewholder.PullResuestsViewHolder;

import java.util.List;

public class ListPullRequestsAdapter extends RecyclerView.Adapter<PullResuestsViewHolder> {

    private List<GitHubPullRequest> pullRequests;

    public ListPullRequestsAdapter(List<GitHubPullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }

    @Override
    public PullResuestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.issues_content, parent, false);

        return new PullResuestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PullResuestsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }
}
