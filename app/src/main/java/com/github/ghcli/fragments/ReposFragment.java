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
import com.github.ghcli.models.GitHubRepository;
import com.github.ghcli.models.GitHubUser;
import com.github.ghcli.service.ServiceGenerator;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.repositoryProgressBar) ProgressBar repositoryProgressBar;
    @BindView(R.id.repositoryRecyclerView) RecyclerView repositoryRecyclerView;

    public ReposFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReposFragment.
     */
    public static ReposFragment newInstance(String param1, String param2) {
        ReposFragment fragment = new ReposFragment();
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
        View view = inflater.inflate(R.layout.fragment_repos, container, false);
        ButterKnife.bind(this, view);

        final IGitHubUser gitHubUserClient = ServiceGenerator.createService(IGitHubUser.class);
        loadUserRepositories(gitHubUserClient);

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

    private void loadUserRepositories(IGitHubUser gitHubUserClient) {
        Call<List<GitHubRepository>> callRepos = gitHubUserClient.getUserRepos(Authentication.getToken(getActivity().getApplicationContext()));
        repositoryProgressBar.setVisibility(View.VISIBLE);
        callRepos.enqueue(new Callback<List<GitHubRepository>>() {
            @Override
            public void onResponse(Call<List<GitHubRepository>> call, Response<List<GitHubRepository>> response) {
                if(response.isSuccessful()) {
                    repositoryProgressBar.setVisibility(View.INVISIBLE);
                    List<GitHubRepository> repositories = response.body();
                    repositoryRecyclerView.setAdapter(new ListRepositoriesAdapter(repositories));
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(
                            getActivity().getApplicationContext(),
                            LinearLayoutManager.VERTICAL,
                            false);
                    repositoryRecyclerView.setLayoutManager(layout);
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepository>> call, Throwable t) {

            }
        });
    }
}
