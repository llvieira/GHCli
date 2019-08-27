package com.github.ghcli.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GitHubUser implements Parcelable {

    private String login;
    private String name;
    private String type;
    private String bio;
    private String email;
    private String location;
    private Integer followers;

    @SerializedName("following")
    private Integer followings;

    @SerializedName("public_repos")
    private Integer publicRepos;

    @SerializedName("total_private_repos")
    private Integer privateRepos;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public GitHubUser() {
    }

    public GitHubUser(String login, String name, String type, String bio, String email, String location, Integer followers, Integer followings, Integer publicRepos, Integer privateRepos, String avatarUrl) {
        this.login = login;
        this.name = name;
        this.type = type;
        this.bio = bio;
        this.email = email;
        this.location = location;
        this.followers = followers;
        this.followings = followings;
        this.publicRepos = publicRepos;
        this.privateRepos = privateRepos;
        this.avatarUrl = avatarUrl;
    }

    protected GitHubUser(Parcel in) {
        login = in.readString();
        name = in.readString();
        type = in.readString();
        bio = in.readString();
        email = in.readString();
        location = in.readString();
        followers = in.readInt();
        followings = in.readInt();
        publicRepos = in.readInt();
        privateRepos = in.readInt();
        avatarUrl = in.readString();
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

    public String getBio() { return bio; }

    public void setBio(String bio) { this.bio = bio; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public Integer getFollowers() { return followers; }

    public void setFollowers(Integer followers) { this.followers = followers; }

    public Integer getFollowings() { return followings; }

    public void setFollowings(Integer followings) { this.followings = followings; }

    public Integer getPublicRepos() { return publicRepos; }

    public void setPublicRepos(Integer publicRepos) { this.publicRepos = publicRepos; }

    public Integer getPrivateRepos() { return privateRepos; }

    public void setPrivateRepos(Integer privateRepos) { this.privateRepos = privateRepos; }

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
                ", bio='" + bio + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeString(bio);
        parcel.writeString(email);
        parcel.writeString(location);
        parcel.writeInt(followers);
        parcel.writeInt(followings);
        parcel.writeInt(publicRepos);
        parcel.writeInt(privateRepos);
        parcel.writeString(avatarUrl);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GitHubUser that = (GitHubUser) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (bio != null ? !bio.equals(that.bio) : that.bio != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        return avatarUrl != null ? avatarUrl.equals(that.avatarUrl) : that.avatarUrl == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        return result;
    }
}
