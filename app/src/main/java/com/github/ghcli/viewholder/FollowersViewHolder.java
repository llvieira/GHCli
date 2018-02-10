package com.github.ghcli.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ghcli.R;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersViewHolder extends RecyclerView.ViewHolder {
    private final String FOLLOW = "FOLLOW";
    private final String UNFOLLOW = "UNFOLLOW";
    @BindView(R.id.follower_name) TextView login;
    @BindView(R.id.follow) Button follow;
    @BindView(R.id.follower_avatar) ImageView avatar;
    private View view;
    private Context context;
    private IGitHubUser iGitHubUser;
    private boolean following;

    public FollowersViewHolder(View itemView, IGitHubUser iGitHubUser, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.view = itemView;
        this.context = context;
        this.iGitHubUser = iGitHubUser;
        this.login.setTextColor(Color.BLACK);
        this.login.setEnabled(false);
        setActionButton();
    }

    private void isFollowing() {
        Call<Void> isFollowing = iGitHubUser.isFollowing(Authentication.getToken(context), login.getText().toString());
        isFollowing.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call,
                                   @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    following = true;
                    follow.setText(UNFOLLOW);
                    follow.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    @OnClick(R.id.follow)
    public void setActionButton() {
        if (following) {
            Call<Void> call = iGitHubUser.unfollow(
                    Authentication.getToken(context),
                    login.getText().toString());

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call,
                                       @NonNull Response<Void> response) {
                    if (response.isSuccessful()) {
                        follow.setText(FOLLOW);
                        following = false;
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        } else {
            Call<Void> call = iGitHubUser.follow(
                    Authentication.getToken(context),
                    login.getText().toString());

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call,
                                       @NonNull Response<Void> response) {
                    if (response.isSuccessful()) {
                        follow.setText(UNFOLLOW);
                        following = true;
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        }
    }

    public TextView getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login.setText(login);
        isFollowing();
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar_url) {
        Picasso.with(view.getContext()).load(avatar_url).into(this.avatar);
    }
}
