package com.github.ghcli.models;

import java.util.List;

public class GitHubPullRequest extends GitHubIssues {

    private PullRequestHead head;

    public GitHubPullRequest() {
    }

    public GitHubPullRequest(String number, String status, String title, String body,
                             List<IssueLabels> labels, PullRequestHead head) {
        super(number, status, title, body, labels);
        this.head = head;

    }

    public PullRequestHead getHead() {
        return head;
    }

    public void setHead(PullRequestHead head) {
        this.head = head;
    }
}
