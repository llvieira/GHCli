package com.github.ghcli.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GitHubRepository {

    private String name;

    @SerializedName("full_name")
    private String fullName;

    private String description;

    private String language;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("fork")
    private boolean isFork;

    @SerializedName("private")
    private boolean isPrivate;

    public GitHubRepository() {
    }

    public GitHubRepository(String name, String fullName, String description, String language, Date updatedAt, boolean isFork, boolean isPrivate) {
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.language = language;
        this.updatedAt = updatedAt;
        this.isFork = isFork;
        this.isPrivate = isPrivate;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public boolean isFork() {
        return isFork;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setFork(boolean fork) {
        this.isFork = fork;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}