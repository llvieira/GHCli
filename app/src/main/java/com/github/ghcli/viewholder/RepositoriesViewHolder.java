package com.github.ghcli.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.ghcli.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoriesViewHolder extends RecyclerView.ViewHolder {

    private static final String UPDATE_STRING = "Updated on ";

    @BindView(R.id.repositoryName) TextView repositoryName;
    @BindView(R.id.repositoryCardPrivate) CardView repositoryCardPrivate;
    @BindView(R.id.repositoryDescription) TextView repositoryDescription;
    @BindView(R.id.repositoryLanguage) TextView repositoryLanguage;
    @BindView(R.id.repositoryLatsUpdate) TextView repositoryLastUpdate;


    public RepositoriesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String name) {
        this.repositoryName.setText(name);
    }

    public TextView getRepositoryDescription() {
        return repositoryDescription;
    }

    public void setRepositoryDescription(String description) {
        this.repositoryDescription.setText(description);
    }

    public TextView getRepositoryLanguage() {
        return repositoryLanguage;
    }

    public void setRepositoryLanguage(String language) {
        this.repositoryLanguage.setText(language);
    }

    public TextView getRepositoryLastUpdate() {
        return repositoryLastUpdate;
    }

    public void setRepositoryLastUpdate(String lastUpdate) {
        this.repositoryLastUpdate.setText(UPDATE_STRING + lastUpdate);
    }

    public CardView getRepositoryCardPrivate() {
        return repositoryCardPrivate;
    }

    public void setRepositoryCardPrivate(boolean isPrivate) {
        int visibility = isPrivate ? View.VISIBLE : View.INVISIBLE;

        this.repositoryCardPrivate.setVisibility(visibility);
    }
}