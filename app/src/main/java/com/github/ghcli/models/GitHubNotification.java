package com.github.ghcli.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GitHubNotification {

    private String reason;

    private GitHubRepository repository;

    private Boolean unread;

    private GitHubNotificationSubject subject;

    public GitHubNotification() {
    }

    public GitHubNotification(String reason, GitHubRepository repository, Boolean unread, GitHubNotificationSubject subject) {
        this.reason = reason;
        this.repository = repository;
        this.unread = unread;
        this.subject = subject;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public GitHubRepository getRepository() {
        return repository;
    }

    public void setRepository(GitHubRepository repository) {
        this.repository = repository;
    }

    public Boolean getUnread() {
        return unread;
    }

    public void setUnread(Boolean unread) {
        this.unread = unread;
    }

    public GitHubNotificationSubject getSubject() {
        return subject;
    }

    public void setSubject(GitHubNotificationSubject subject) {
        this.subject = subject;
    }
}