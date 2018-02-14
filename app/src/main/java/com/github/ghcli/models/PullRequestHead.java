package com.github.ghcli.models;

import com.google.gson.annotations.SerializedName;

public class PullRequestHead {

    @SerializedName("repo")
    private GitHubRepository repository;

    public PullRequestHead() {
    }

    public PullRequestHead(GitHubRepository repository) {
        this.repository = repository;
    }

    public GitHubRepository getRepository() {
        return repository;
    }

    public void setRepository(GitHubRepository repository) {
        this.repository = repository;
    }
}
