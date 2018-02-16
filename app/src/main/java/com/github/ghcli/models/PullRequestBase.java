package com.github.ghcli.models;

import com.google.gson.annotations.SerializedName;

public class PullRequestBase {

    @SerializedName("repo")
    private GitHubRepository repository;

    public PullRequestBase() {
    }

    public PullRequestBase(GitHubRepository repository) {
        this.repository = repository;
    }

    public GitHubRepository getRepository() {
        return repository;
    }

    public void setRepository(GitHubRepository repository) {
        this.repository = repository;
    }
}
