package com.github.ghcli.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by davi on 12/02/2018.
 */

public class GitHubRepositorie {

    private String name;
    private String full_name;
    private String description;
    private String language;
    private boolean fork;

    @SerializedName("private")
    private boolean isPrivate;


}
