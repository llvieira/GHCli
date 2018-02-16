package com.github.ghcli.models;

import java.util.List;

public class GitHubPullRequest extends GitHubIssues {

    private PullRequestBase base;

    public GitHubPullRequest() {
    }

    public GitHubPullRequest(String number, String status, String title, String body,
                             List<IssueLabels> labels, PullRequestBase base) {
        super(number, status, title, body, labels);
        this.base = base;

    }

    public PullRequestBase getBase() {
        return base;
    }

    public void setBase(PullRequestBase base) {
        this.base = base;
    }
}
