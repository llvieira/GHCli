package com.github.ghcli.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.viewholder.FollowersViewHolder;
import com.github.ghcli.models.GitHubUser;
import com.github.ghcli.service.clients.IGitHubUser;

import java.util.List;

public class ListFollowersAdapter extends RecyclerView.Adapter {

    private List<GitHubUser> followers;
    private Context context;
    private IGitHubUser iGitHubUser;

    public ListFollowersAdapter(List<GitHubUser> followers, Context context, IGitHubUser iGitHubUser) {
        this.followers = followers;
        this.context = context;
        this.iGitHubUser = iGitHubUser;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.follower_content, parent, false);

        FollowersViewHolder viewHolder = new FollowersViewHolder(view, iGitHubUser, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FollowersViewHolder followersViewHolder = (FollowersViewHolder) holder;
        GitHubUser follower = followers.get(position);
        followersViewHolder.setLogin(follower.getLogin());
        followersViewHolder.setAvatar(follower.getAvatarUrl());
    }

    @Override
    public int getItemCount() {
        return followers.size();
    }
}
