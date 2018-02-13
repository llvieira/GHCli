package com.github.ghcli.models;

/**
 * Created by pedro on 13/02/18.
 */

public class GitRepository {

    private String id;
    private String name;
    private String url;

    public GitRepository() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
