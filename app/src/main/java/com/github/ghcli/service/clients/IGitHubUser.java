package com.github.ghcli.service.clients;

import com.github.ghcli.models.GitHubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Interface to get the GitHub User.
 */
public interface IGitHubUser {

    @GET("user")
    Call<GitHubUser> getUser(@Header("Authorization") String credentials);
}
