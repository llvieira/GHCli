package com.github.ghcli.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ghcli.R;
import com.github.ghcli.models.GitHubRepository;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;
import com.github.ghcli.util.Message;
import com.github.ghcli.viewholder.StarredReposViewHolder;

import java.text.DateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListStarredReposAdapter extends RecyclerView.Adapter<StarredReposViewHolder> {

    private static final String MESSAGE_UNSTAR_SUCCESSFUL = "unstar successful";
    private static final String MESSAGE_UNSTAR_FAIL = "Could not unstar";
    private static final String MESSAGE_UNSTAR_NO_RESPONSE = "There is no response from the server";

    private List<GitHubRepository> starredRepos;
    private Context context;
    private IGitHubUser gitHubUserClient;

    public ListStarredReposAdapter(List<GitHubRepository> starredRepos, IGitHubUser gitHubUserClient, Context context) {
        this.starredRepos = starredRepos;
        this.gitHubUserClient = gitHubUserClient;
        this.context = context;
    }

    @Override
    public StarredReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.starred_repos_content, parent, false);
        StarredReposViewHolder holderStarredRepos = new StarredReposViewHolder(view);

        return holderStarredRepos;
    }

    @Override
    public void onBindViewHolder(StarredReposViewHolder holder, final int position) {
        final GitHubRepository starredRepo = starredRepos.get(position);
        String dateStarredRepo = DateFormat.getDateInstance(DateFormat.LONG).format(starredRepo.getUpdatedAt());

        if (starredRepo.getName() != null) {
            holder.setStarredReposName(starredRepo.getFullName());
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

        holder.getStarredReposUnstar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unstarRepository(starredRepo.getOwner().getLogin(), starredRepo.getName(), position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return starredRepos.size();
    }

    public void deleteItem(int position) {
        starredRepos.remove(position);
        notifyItemRemoved(position);
    }

    private void unstarRepository(String ownerRepository, String nameRepository, final int positionRepository) {
        Call<Void> callUnstarRepo = this.gitHubUserClient.unstar(Authentication.getToken(this.context), ownerRepository, nameRepository);

        callUnstarRepo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    deleteItem(positionRepository);
                    Message.showToast(MESSAGE_UNSTAR_SUCCESSFUL, context);
                } else {
                    Message.showToast(MESSAGE_UNSTAR_FAIL, context);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Message.showToast(MESSAGE_UNSTAR_NO_RESPONSE, context);
            }
        });
    }
}