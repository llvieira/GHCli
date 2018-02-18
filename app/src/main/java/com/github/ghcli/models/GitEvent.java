package com.github.ghcli.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GitEvent {

    private String type;
    private GitHubUser actor;
    private GitHubRepository repo;

    @SerializedName("created_at")
    private Date date;

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

    public GitHubRepository getRepo() {
        return repo;
    }

    public void setRepo(GitHubRepository repo) {
        this.repo = repo;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
