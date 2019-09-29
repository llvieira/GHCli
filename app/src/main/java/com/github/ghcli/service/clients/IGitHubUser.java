package com.github.ghcli.service.clients;

import com.github.ghcli.models.GitHubIssues;
import com.github.ghcli.models.GitHubOrganization;
import com.github.ghcli.models.GitHubPullRequest;
import com.github.ghcli.models.GitHubRepository;
import com.github.ghcli.models.GitHubUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface to get the GitHub User.
 */
public interface IGitHubUser {

    @GET("user")
    Call<GitHubUser> getUser(@Header("Authorization") String credentials);

    @GET("user/orgs")
    Call<ArrayList<GitHubOrganization>> getUserOrgs(@Header("Authorization") String credentials);

    @GET("user/repos")
    Call<List<GitHubRepository>> getUserRepos(@Header("Authorization") String credentials);

    @GET("user/starred")
    Call<List<GitHubRepository>> getUserStarredRepos(@Header("Authorization") String credentials);

    @GET("user/followers")
    Call<List<GitHubUser>> getFollowers(@Header("Authorization") String credentials);

    @GET("user/following")
    Call<List<GitHubUser>> getFollowing(@Header("Authorization") String credentials);

    @GET("users/{user}")
    Call<GitHubUser> getOneUser(@Header("Authorization") String credentials, @Path("user") String user);

    @GET("user/following/{user}")
    Call<Void> isFollowing(@Header("Authorization") String credentials, @Path("user") String user);

    @GET("/user/issues")
    Call<List<GitHubIssues>> getIssues(@Header("Authorization") String credentials,
                                       @Query("state") String state,
                                       @Query("filter") String filter);

    @GET("/repos/{owner}/{repo}/pulls/{number}")
    Call<GitHubPullRequest> getPullResquet(@Header("Authorization") String credentials,
                                           @Path("owner") String owner,
                                           @Path("repo") String repo,
                                           @Path("number") String number);

    @PUT("user/following/{user}")
    Call<Void> follow(@Header("Authorization") String credentials, @Path("user") String user);

    @PUT("notifications")
    Call<Void> markNotificationAsRead(@Header("Authorization") String credentials, @Body HashMap<String, String> lastRead);

    @DELETE("user/following/{user}")
    Call<Void> unfollow(@Header("Authorization") String credentials, @Path("user") String user);

    @DELETE("user/starred/{ownerRepo}/{nameRepo}")
    Call<Void> unstar(@Header("Authorization") String credentials, @Path("ownerRepo") String ownerRepo, @Path("nameRepo") String nameRepo);
}
