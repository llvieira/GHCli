package com.github.ghcli.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by davi on 18/01/2018.
 */

public class GitHubOrganization implements Parcelable {

    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public GitHubOrganization () {
    }

    public GitHubOrganization (String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    protected GitHubOrganization (Parcel in) {
        login = in.readString();
        avatarUrl = in.readString();
    }

    public static final Creator<GitHubOrganization> CREATOR = new Creator<GitHubOrganization>() {
        @Override
        public GitHubOrganization createFromParcel(Parcel in) {
            return new GitHubOrganization(in);
        }

        @Override
        public GitHubOrganization[] newArray(int size) { return new GitHubOrganization[size]; }
    };

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GitHubOrganization that = (GitHubOrganization) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return avatarUrl != null ? avatarUrl.equals(that.avatarUrl) : that.avatarUrl == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeString(avatarUrl);
    }
}
