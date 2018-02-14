package com.github.ghcli.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.models.IssueLabels;
import com.github.ghcli.viewholder.LabelsViewHolder;

import java.util.List;

public class LabelsAdapter extends RecyclerView.Adapter<LabelsViewHolder> {
    private List<IssueLabels> labels;

    public LabelsAdapter(List<IssueLabels> labels) {
        this.labels = labels;
    }

    @Override
    public LabelsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.labels_card, parent, false);

        return new LabelsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LabelsViewHolder holder, int position) {
        IssueLabels issueLabels = labels.get(position);
        holder.setLabel(issueLabels);
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }
}
