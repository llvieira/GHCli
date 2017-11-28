package com.github.ghcli.models;

import com.google.gson.annotations.SerializedName;

public class GitHubUser {

    private String login;
    private String name;
    private String type;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public GitHubUser() {
    }

    public GitHubUser(String login, String name, String type, String avatarUrl) {
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
