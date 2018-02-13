package com.github.ghcli.models;

import com.google.gson.annotations.SerializedName;

public class GitEvent {

    private String type;
    private GitHubUser actor;
    private GitRepository repo;

    @SerializedName("created_at")
    private String date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GitHubUser getActor() {
        return actor;
    }

    public void setActor(GitHubUser actor) {
        this.actor = actor;
    }

    public GitRepository getRepo() {
        return repo;
    }

    public void setRepo(GitRepository repo) {
        this.repo = repo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
