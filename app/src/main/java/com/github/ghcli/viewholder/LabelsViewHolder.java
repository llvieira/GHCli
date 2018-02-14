package com.github.ghcli.viewholder;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.ghcli.R;
import com.github.ghcli.models.IssueLabels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LabelsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.labels_card_view) CardView cardView;
    @BindView(R.id.labels_text) TextView labelText;

    private View view;

    public LabelsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.view = itemView;
    }

    public void setLabel(IssueLabels issueLabels) {
        labelText.setText(issueLabels.getName());
        cardView.setCardBackgroundColor(Color.parseColor(issueLabels.getColor()));
    }
}
