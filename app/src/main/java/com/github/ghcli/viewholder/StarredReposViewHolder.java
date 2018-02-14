package com.github.ghcli.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.ghcli.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StarredReposViewHolder extends RecyclerView.ViewHolder {

    private static final String UPDATE_STRING = "Updated on ";

    @BindView(R.id.starredReposName) TextView starredReposName;
    @BindView(R.id.starredReposUnstar) Button starredReposUnstar;
    @BindView(R.id.starredReposDescription) TextView starredReposDescription;
    @BindView(R.id.starredReposLanguage) TextView starredReposLanguage;
    @BindView(R.id.starredReposStarNumber) TextView starredReposStarNumber;
    @BindView(R.id.starredReposLastUpdate) TextView starredReposLastUpdate;

    public StarredReposViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getStarredReposName() {
        return starredReposName;
    }

    public void setStarredReposName(String name) {
        this.starredReposName.setText(name);
    }

    public TextView getStarredReposDescription() {
        return starredReposDescription;
    }

    public void setStarredReposDescription(String description) {
        this.starredReposDescription.setText(description);
    }

    public TextView getStarredReposLanguage() {
        return starredReposLanguage;
    }

    public void setStarredReposLanguage(String language) {
        this.starredReposLanguage.setText(language);
    }

    public TextView getStarredReposStarNumber() {
        return starredReposStarNumber;
    }

    public void setStarredReposStarNumber(int numberStar) {
        this.starredReposLanguage.setText(String.valueOf(numberStar));
    }

    public TextView getStarredReposLastUpdate() {
        return starredReposLastUpdate;
    }

    public void setStarredReposLastUpdate(String lastUpdate) {
        this.starredReposLastUpdate.setText(UPDATE_STRING + lastUpdate);
    }
}
