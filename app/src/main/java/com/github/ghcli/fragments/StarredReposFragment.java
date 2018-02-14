package com.github.ghcli.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.ghcli.R;
import com.github.ghcli.adapter.ListRepositoriesAdapter;
import com.github.ghcli.adapter.ListStarredReposAdapter;
import com.github.ghcli.models.GitHubRepository;
import com.github.ghcli.service.ServiceGenerator;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarredReposFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.starredReposProgressBar) ProgressBar starredReposProgressBar;
    @BindView(R.id.starredReposRecyclerView) RecyclerView starredReposRecyclerView;


    public StarredReposFragment() {
        // Required empty public constructor
    }

    public static StarredReposFragment newInstance(String param1, String param2) {
        StarredReposFragment fragment = new StarredReposFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_starred_repos, container, false);
        ButterKnife.bind(this, view);

        final IGitHubUser gitHubUserClient = ServiceGenerator.createService(IGitHubUser.class);
        loadUserStarredRepos(gitHubUserClient);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void loadUserStarredRepos(final IGitHubUser gitHubUserClient) {
        Call<List<GitHubRepository>> callStarRepos = gitHubUserClient.getUserStarredRepos(Authentication.getToken(getActivity().getApplicationContext()));
        starredReposProgressBar.setVisibility(View.VISIBLE);
        callStarRepos.enqueue(new Callback<List<GitHubRepository>>() {
            @Override
            public void onResponse(Call<List<GitHubRepository>> call, Response<List<GitHubRepository>> response) {
                if(response.isSuccessful()) {
                    starredReposProgressBar.setVisibility(View.INVISIBLE);
                    List<GitHubRepository> starredRepos = response.body();
                    starredReposRecyclerView.setAdapter(new ListStarredReposAdapter(starredRepos, gitHubUserClient, getActivity().getApplicationContext()));
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(
                            getActivity().getApplicationContext(),
                            LinearLayoutManager.VERTICAL,
                            false);
                    starredReposRecyclerView.setLayoutManager(layout);
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepository>> call, Throwable t) {

            }
        });
    }
}