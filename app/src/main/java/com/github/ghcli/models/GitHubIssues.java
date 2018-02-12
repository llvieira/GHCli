package com.github.ghcli.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class GitHubIssues implements Parcelable {

    private String number;
    private String state;
    private String title;
    private String body;
    private List<IssueLabels> labels;

    public GitHubIssues() {
    }

    public GitHubIssues(String number, String state, String title, String body, List<IssueLabels> labels) {
        this.number = number;
        this.state = state;
        this.title = title;
        this.body = body;
        this.labels = labels;
    }

    protected GitHubIssues(Parcel in) {
        number = in.readString();
        state = in.readString();
        title = in.readString();
        body = in.readString();
        labels = new ArrayList<IssueLabels>();
        in.readList(new ArrayList<IssueLabels>(), IssueLabels.class.getClassLoader());
    }

    public static final Creator<GitHubUser> CREATOR = new Creator<GitHubUser>() {
        @Override
        public GitHubUser createFromParcel(Parcel in) {
            return new GitHubUser(in);
        }

        @Override
        public GitHubUser[] newArray(int size) {
            return new GitHubUser[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        return labels;
    }

    public void setLabels(List<IssueLabels> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "GitHubIssues{" +
                "number='" + number + '\'' +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", labels='" + labels + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(number);
        parcel.writeString(state);
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeList(labels);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GitHubIssues that = (GitHubIssues) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        return labels != null ? labels.equals(that.labels) : that.labels == null;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (labels != null ? labels.hashCode() : 0);
        return result;
    }
}
