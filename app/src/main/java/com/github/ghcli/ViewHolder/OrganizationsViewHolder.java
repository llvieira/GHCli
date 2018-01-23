package com.github.ghcli.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ghcli.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrganizationsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.organization_login) TextView loginOrganization;
    @BindView(R.id.organization_avatar) ImageView avatarOrganization;
    private View view;

    public OrganizationsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.view = itemView;
    }

    public TextView getLoginOrganization() {
        return loginOrganization;
    }

    public void setLoginOrganization(String login) {
        this.loginOrganization.setText(login);
    }

    public ImageView getAvatarOrganization() {
        return avatarOrganization;
    }

    public void setAvatarOrganization(String avatarUrl) {
        Picasso.with(view.getContext()).load(avatarUrl).into(this.avatarOrganization);
    }
}
