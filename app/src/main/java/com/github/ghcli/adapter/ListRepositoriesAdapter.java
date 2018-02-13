package com.github.ghcli.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.models.GitHubRepository;
import com.github.ghcli.viewholder.RepositoriesViewHolder;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

public class ListRepositoriesAdapter extends RecyclerView.Adapter<RepositoriesViewHolder> {

    private List<GitHubRepository> repositories;

    public ListRepositoriesAdapter(List<GitHubRepository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public RepositoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.repositories_content, parent, false);

        RepositoriesViewHolder holderRepository = new RepositoriesViewHolder(view);

        return holderRepository;
    }

    @Override
    public void onBindViewHolder(RepositoriesViewHolder holder, int position) {
        GitHubRepository repository = repositories.get(position);
        String dateRepository = DateFormat.getDateInstance(DateFormat.LONG).format(repository.getUpdatedAt());

        if (repository.getName() != null) {
            holder.setRepositoryName(repository.getName());
        }

        if (repository.getDescription() != null) {
            holder.setRepositoryDescription(repository.getDescription());
        }

        if (repository.getLanguage() != null) {
            holder.setRepositoryLanguage(repository.getLanguage());
        }

        if (dateRepository != null && !dateRepository.isEmpty()) {
            holder.setRepositoryLastUpdate(dateRepository);
        }

        holder.setRepositoryCardPrivate(repository.isPrivate());

    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }
}
