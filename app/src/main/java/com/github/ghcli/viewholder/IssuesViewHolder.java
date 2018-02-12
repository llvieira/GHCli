package com.github.ghcli.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.ghcli.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IssuesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.issue_number) TextView issueNumber;
    @BindView(R.id.issue_state) TextView issueState;
    @BindView(R.id.issue_title) TextView issueTitle;
    @BindView(R.id.issue_body) TextView issueBody;

    private View view;

    public IssuesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.view = itemView;
    }

    public TextView getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber.setText(issueNumber);
    }

    public void setIssueState(String issueState) {
        this.issueState.setText(issueState);
    }

    public TextView getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle.setText(issueTitle);
    }

    public TextView getIssueBody() {
        return issueBody;
    }

    public void setIssueBody(String issueBody) {
        this.issueBody.setText(issueBody);
    }
}
