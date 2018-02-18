package com.github.ghcli.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.ghcli.R;
import com.github.ghcli.adapter.ListBrowserFollowingAdapter;
import com.github.ghcli.adapter.ListFollowersAdapter;
import com.github.ghcli.adapter.ListMyFollowingAdapter;
import com.github.ghcli.models.GitEvent;
import com.github.ghcli.models.GitHubUser;
import com.github.ghcli.service.ServiceGenerator;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BrowserFollowingFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rvListBrowserFollowing) RecyclerView recyclerView;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private IGitHubUser iGitHubUser;
    private Context context;

    public BrowserFollowingFragment() {
        // Required empty public constructor
    }

    public static BrowserFollowingFragment newInstance(String param1, String param2) {
        BrowserFollowingFragment fragment = new BrowserFollowingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        fragment.setiGitHubUser(ServiceGenerator.createService(IGitHubUser.class));
        return fragment;
    }

    private GitEvent getOneEvent(String user) {
        Call<List<GitEvent>> callEventsFollowing = iGitHubUser.getEventFollowing(Authentication.getToken(context), user);
        List<GitEvent> gitEvents = new ArrayList<>();
        try {
            gitEvents = callEventsFollowing.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(gitEvents.isEmpty()){
            return null;
        }else{
            return gitEvents.get(0);
        }
    }

    private void getEventsFollowing(){

        Call<List<GitHubUser>> gitHubUserCall = iGitHubUser.getFollowing(Authentication.getToken(context));

        gitHubUserCall.enqueue(new Callback<List<GitHubUser>>() {
            List<GitHubUser> gitHubUsers = new ArrayList<>();
            List<GitEvent> gitEvents = new ArrayList<>();
            @Override
            public void onResponse(Call<List<GitHubUser>> call, Response<List<GitHubUser>> response) {
                gitHubUsers = response.body();

                for(GitHubUser gitHubUser : gitHubUsers){
                    GitEvent gitEvent =getOneEvent(gitHubUser.getLogin());
                    if(gitEvent != null){
                        gitEvent.getActor().setName(gitHubUser.getLogin());
                        gitEvents.add(gitEvent);
                    }
                }

                recyclerView.setAdapter(new ListBrowserFollowingAdapter(context, gitEvents,iGitHubUser));
                RecyclerView.LayoutManager layout = new LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL,
                        false);
                recyclerView.setLayoutManager(layout);

            }

            @Override
            public void onFailure(Call<List<GitHubUser>> call, Throwable t) {

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
        View view = inflater.inflate(R.layout.fragment_browser_following, container, false);
        ButterKnife.bind(this, view);
        this.context = getActivity().getApplicationContext();
        getEventsFollowing();
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public IGitHubUser getiGitHubUser() {
        return iGitHubUser;
    }

    public void setiGitHubUser(IGitHubUser iGitHubUser) {
        this.iGitHubUser = iGitHubUser;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}