package com.github.ghcli.viewholder;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.ghcli.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullResuestsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.issue_number) TextView pullRequestNumber;
    @BindView(R.id.issue_status) TextView pullRequestStatus;
    @BindView(R.id.issue_title) TextView pullRequestTitle;
    @BindView(R.id.issue_body) TextView pullRequestBody;

    public PullResuestsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getPullRequestNumber() {
        return pullRequestNumber;
    }

    public void setPullRequestNumber(String pullRequestNumber) {
        this.pullRequestNumber.setText(pullRequestNumber);
    }

    public void setPullRequestStatus(String pullRequestStatus) {
        if (pullRequestStatus != null && pullRequestStatus.equals("closed")) {
            this.pullRequestStatus.setTextColor(Color.RED);
        } else {
            this.pullRequestStatus.setTextColor(Color.BLUE);
        }

        String newStr = pullRequestStatus.substring(0, 1).toUpperCase() + pullRequestStatus.substring(1);

        this.pullRequestStatus.setText(newStr);
    }

    public TextView getPullRequestTitle() {
        return pullRequestTitle;
    }

    public void setPullRequestTitle(String pullRequestTitle) {
        this.pullRequestTitle.setText(pullRequestTitle);
    }

    public TextView getPullRequestBody() {
        return pullRequestBody;
    }

    public void setPullRequestBody(String pullRequestBody) {
        if (pullRequestBody != null && pullRequestBody.isEmpty()) {
            this.pullRequestBody.setText("No description...");

        } else {
            this.pullRequestBody.setText(pullRequestBody);
        }
    }
}
