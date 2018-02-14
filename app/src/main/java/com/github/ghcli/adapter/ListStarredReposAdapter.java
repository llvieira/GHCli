package com.github.ghcli.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.models.GitHubRepository;
import com.github.ghcli.viewholder.StarredReposViewHolder;

import java.text.DateFormat;
import java.util.List;

public class ListStarredReposAdapter extends RecyclerView.Adapter<StarredReposViewHolder> {

    private List<GitHubRepository> starredRepos;

    public ListStarredReposAdapter(List<GitHubRepository> starredRepos) {
        this.starredRepos = starredRepos;
    }

    @Override
    public StarredReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.starred_repos_content, parent, false);

        StarredReposViewHolder holderStarredRepos = new StarredReposViewHolder(view);

        return holderStarredRepos;
    }

    @Override
    public void onBindViewHolder(StarredReposViewHolder holder, int position) {
        GitHubRepository starredRepo = starredRepos.get(position);
        String dateStarredRepo = DateFormat.getDateInstance(DateFormat.LONG).format(starredRepo.getUpdatedAt());

        if (starredRepo.getName() != null) {
            holder.setStarredReposName(starredRepo.getName());
        }

        if (starredRepo.getDescription() != null) {
            holder.setStarredReposDescription(starredRepo.getDescription());
        }

        if (starredRepo.getLanguage() != null) {
            holder.setStarredReposLanguage(starredRepo.getLanguage());
        }

        holder.setStarredReposStarNumber(starredRepo.getStargazersCount());

        if (dateStarredRepo != null && !dateStarredRepo.isEmpty()) {
            holder.setStarredReposLastUpdate(dateStarredRepo);
        }
    }

    @Override
    public int getItemCount() {
        return starredRepos.size();
    }
}
