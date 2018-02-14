package com.github.ghcli.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.models.GitHubIssues;
import com.github.ghcli.viewholder.IssuesViewHolder;

import java.util.List;

public class ListIssuesAdapter extends RecyclerView.Adapter<IssuesViewHolder> {
    private List<GitHubIssues> issues;

    public ListIssuesAdapter(List<GitHubIssues> issues) {
        this.issues = issues;
    }

    @Override
    public IssuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.issues_content, parent, false);

        return new IssuesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IssuesViewHolder holder, int position) {
        GitHubIssues gitHubIssues = issues.get(position);
        holder.setOwnerRepo(gitHubIssues.getRepository().getFullName());
        holder.setIssueNumber(gitHubIssues.getNumber());
        holder.setIssueStatus(gitHubIssues.getStatus());
        holder.setIssueTitle(gitHubIssues.getTitle());
        holder.setIssueBody(gitHubIssues.getBody());
        holder.setLabelRecyclerView(gitHubIssues.getLabels());
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }
}
