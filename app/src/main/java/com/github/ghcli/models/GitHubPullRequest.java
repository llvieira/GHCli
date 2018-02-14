package com.github.ghcli.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GitHubPullRequest implements Parcelable {

    private String number;
    @SerializedName("state")
    private String status;
    private String title;
    private String body;


    public GitHubPullRequest() {
    }

    public GitHubPullRequest(String number, String status, String title, String body) {
        this.number = number;
        this.status = status;
        this.title = title;
        this.body = body;
    }

    public GitHubPullRequest(Parcel in) {
        number = in.readString();
        status = in.readString();
        title = in.readString();
        body = in.readString();
    }

    public static final Creator<GitHubPullRequest> CREATOR = new Creator<GitHubPullRequest>() {
        @Override
        public GitHubPullRequest createFromParcel(Parcel parcel) {
            return new GitHubPullRequest((parcel));
        }

        @Override
        public GitHubPullRequest[] newArray(int size) {
            return new GitHubPullRequest[size];
        }
    };

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "GitHubPullRequest{" +
                "number='" + number + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(number);
        parcel.writeString(status);
        parcel.writeString(title);
        parcel.writeString(body);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GitHubPullRequest that = (GitHubPullRequest) o;

        if (!getNumber().equals(that.getNumber())) return false;
        if (!getStatus().equals(that.getStatus())) return false;
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null)
            return false;
        return getBody() != null ? getBody().equals(that.getBody()) : that.getBody() == null;
    }

    @Override
    public int hashCode() {
        int result = getNumber().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
        return result;
    }
}
