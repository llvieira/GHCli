package com.github.ghcli.fragments;


import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.ghcli.R;
import com.github.ghcli.adapter.ListIssuesAdapter;
import com.github.ghcli.models.GitHubIssues;
import com.github.ghcli.models.GitHubPullRequest;
import com.github.ghcli.models.IssueLabels;
import com.github.ghcli.service.ServiceGenerator;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequesFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.issue_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.issues_recyclerView)
    RecyclerView recyclerView;

    private String mParam1;
    private String mParam2;

    private IGitHubUser iGitHubUser;
    private Context context;
    private PullRequesFragment.OnFragmentInteractionListener mListener;
    private int processingGetPullRequest = 0;
    private List<GitHubPullRequest> pullRequests = new ArrayList<>();

    public PullRequesFragment() {
    }

    public static PullRequesFragment newInstance(String param1, String param2) {
        PullRequesFragment fragment = new PullRequesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        fragment.setiGitHubUser(ServiceGenerator.createService(IGitHubUser.class));

        return fragment;
    }

    private void getIssues() {
        Call<List<GitHubIssues>> getIssues = iGitHubUser.getIssues(
                Authentication.getToken(context), "all", "all");
        progressBar.setVisibility(View.VISIBLE);
        getIssues.enqueue(new Callback<List<GitHubIssues>>() {
            @Override
            public void onResponse(@NonNull Call<List<GitHubIssues>> call,
                                   @NonNull Response<List<GitHubIssues>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    List<GitHubIssues> issues = response.body();

                    recyclerView.setAdapter(new ListIssuesAdapter(issues));
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(
                            context,
                            LinearLayoutManager.VERTICAL,
                            false);
                    recyclerView.setLayoutManager(layout);
                }
            }

            @Override
            public void onFailure(Call<List<GitHubIssues>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    private void getPullRequests(List<GitHubIssues> issues) {
        for (int i = 0; i < issues.size(); i++) {
            Map<String, String> pullRequest = issues.get(i).getPullRequest();

            if (pullRequest != null) {
                processingGetPullRequest += 1;
                getPullRequest(pullRequest.get("url"));
            }
        }
    }

    private void getPullRequest(String uri) {
        String[] params = uri.split("/");

        String owner = params[4];
        String repo = params[5];
        String number = params[7];

        final Call<GitHubPullRequest> pullRequestCall = iGitHubUser.getPullResquet(owner, repo, number);

        pullRequestCall.enqueue(new Callback<GitHubPullRequest>() {
            @Override
            public void onResponse(@NonNull Call<GitHubPullRequest> call,
                                   @NonNull Response<GitHubPullRequest> response) {
                if (response.isSuccessful()) {
                    pullRequests.add(response.body());
                    processingGetPullRequest -= 1;

                    if (processingGetPullRequest == 0) {

                    }
                }
            }

            @Override
            public void onFailure(Call<GitHubPullRequest> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_issues, container, false);
        ButterKnife.bind(this, view);
        this.context = getActivity().getApplicationContext();
        getIssues();
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
        if (context instanceof PullRequesFragment.OnFragmentInteractionListener) {
            mListener = (PullRequesFragment.OnFragmentInteractionListener) context;
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

    public void setiGitHubUser(IGitHubUser iGitHubUser) {
        this.iGitHubUser = iGitHubUser;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
