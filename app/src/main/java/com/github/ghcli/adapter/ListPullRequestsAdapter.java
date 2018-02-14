package com.github.ghcli.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.models.GitHubPullRequest;
import com.github.ghcli.viewholder.PullRequestsViewHolder;

import java.util.List;

public class ListPullRequestsAdapter extends RecyclerView.Adapter<PullRequestsViewHolder> {

    private List<GitHubPullRequest> pullRequests;

    public ListPullRequestsAdapter(List<GitHubPullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }

    @Override
    public PullRequestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.issues_content, parent, false);
        return new PullRequestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PullRequestsViewHolder holder, int position) {
        GitHubPullRequest pullRequest = pullRequests.get(position);
        holder.setOwnerRepo(pullRequest.getHead().getRepository().getFullName());
        holder.setPullRequestNumber(pullRequest.getNumber());
        holder.setPullRequestStatus(pullRequest.getStatus());
        holder.setPullRequestTitle(pullRequest.getTitle());
        holder.setPullRequestBody(pullRequest.getBody());
        holder.setLabelRecyclerView(pullRequest.getLabels());
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }
}
