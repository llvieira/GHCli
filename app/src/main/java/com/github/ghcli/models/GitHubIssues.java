package com.github.ghcli.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class GitHubIssues {

    private String number;
    @SerializedName("state")
    private String status;
    private String title;
    private String body;
    private List<IssueLabels> labels;
    @SerializedName("pull_request")
    private Map<String, String> pullRequest;
    private GitHubRepository repository;

    public GitHubIssues() {
    }

    public GitHubIssues(String number, String status, String title, String body,
                        List<IssueLabels> labels, Map<String, String> pullRequest,
                        GitHubRepository repository) {
        this.number = number;
        this.status = status;
        this.title = title;
        this.body = body;
        this.labels = labels;
        this.pullRequest = pullRequest;
        this.repository = repository;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<IssueLabels> getLabels() {
        return this.labels;
    }

    public void setLabels(List<IssueLabels> labels) {
        this.labels = labels;
    }

    public Map<String, String> getPullRequest() {
        return pullRequest;
    }

    public void setPullRequest(Map<String, String> pullRequest) {
        this.pullRequest = pullRequest;
    }

    public GitHubRepository getRepository() {
        return repository;
    }

    public void setRepository(GitHubRepository repository) {
        this.repository = repository;
    }

    @Override
    public String toString() {
        return "GitHubIssues{" +
                "number='" + number + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", labels=" + labels +
                ", repository=" + repository +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GitHubIssues that = (GitHubIssues) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (labels != null ? !labels.equals(that.labels) : that.labels != null) return false;
        return repository != null ? repository.equals(that.repository) : that.repository == null;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (labels != null ? labels.hashCode() : 0);
        result = 31 * result + (repository != null ? repository.hashCode() : 0);
        return result;
    }
}
