package com.github.ghcli.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.ViewHolder.OrganizationsViewHolder;
import com.github.ghcli.models.GitHubOrganization;

import java.util.List;

public class ListOrganizationsAdapter extends RecyclerView.Adapter<OrganizationsViewHolder> {

    private List<GitHubOrganization> organizations;

    public ListOrganizationsAdapter(List<GitHubOrganization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public OrganizationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.organization_content, parent, false);

        OrganizationsViewHolder holderOrganization = new OrganizationsViewHolder(view);

        return holderOrganization;
    }

    @Override
    public void onBindViewHolder(OrganizationsViewHolder holder, int position) {
        GitHubOrganization organization = organizations.get(position);
        holder.setLoginOrganization(organization.getLogin());
        holder.setAvatarOrganization(organization.getAvatarUrl());
    }

    @Override
    public int getItemCount() {
        return organizations.size();
    }
}
