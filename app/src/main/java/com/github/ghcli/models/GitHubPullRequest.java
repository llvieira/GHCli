package com.github.ghcli.models;

import java.util.List;

public class GitHubPullRequest extends GitHubIssues {

    private PullRequestHead base;

    public GitHubPullRequest() {
    }

    public GitHubPullRequest(String number, String status, String title, String body,
                             List<IssueLabels> labels, PullRequestHead base) {
        super(number, status, title, body, labels);
        this.base = base;

    }

    public PullRequestHead getBase() {
        return base;
    }

    public void setBase(PullRequestHead base) {
        this.base = base;
    }
}
