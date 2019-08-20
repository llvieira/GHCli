package com.github.ghcli.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.ghcli.R;
import com.github.ghcli.adapter.ListFollowersAdapter;
import com.github.ghcli.adapter.ListMyFollowingAdapter;
import com.github.ghcli.models.GitHubUser;
import com.github.ghcli.service.ServiceGenerator;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFollowingFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Boolean flag = false;

    @BindView(R.id.followersProgressBar)
    ProgressBar progressBar;
    @BindView(R.id.followers_recyclerView)
    RecyclerView recyclerView;

    private String mParam1;
    private String mParam2;

    private FollowersFragment.OnFragmentInteractionListener mListener;
    private IGitHubUser iGitHubUser;
    private Context context;



    public MyFollowingFragment() {

    }

    public static FollowersFragment newInstance(String param1, String param2) {
        //MyFollowingFragment fragment = new MyFollowingFragment();
        FollowersFragment fragment = new FollowersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        fragment.setiGitHubUser(ServiceGenerator.createService(IGitHubUser.class));
        return fragment;
    }

    private void getFollowings() {
        Call<List<GitHubUser>> callFollowers = iGitHubUser.getFollowing(Authentication.getToken(context));
        final List<GitHubUser> gitHubUsers = new ArrayList<>();
        final List<GitHubUser> myFollowers = new ArrayList<>();

        progressBar.setVisibility(View.VISIBLE);
        Log.d("Tetet", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            callFollowers.enqueue(new Callback<List<GitHubUser>>() {
                @Override
                public void onResponse(Call<List<GitHubUser>> call, Response<List<GitHubUser>> response) {
                    if (response.isSuccessful()) {
                        gitHubUsers.addAll(response.body());

                        for (GitHubUser gitHubUser : gitHubUsers) {

                            final Call<GitHubUser> callMyFollowing = iGitHubUser.getOneUser(Authentication.getToken(context), gitHubUser.getLogin());
                            callMyFollowing.enqueue(new Callback<GitHubUser>() {
                                @Override
                                public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                                    if (response.isSuccessful()) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        myFollowers.add(response.body());
                                        //System.out.println(myFollowers);
                                        Log.d("Tetet", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

                                        recyclerView.setAdapter(new ListFollowersAdapter(myFollowers, context , iGitHubUser));
                                        RecyclerView.LayoutManager layout = new LinearLayoutManager(
                                                context,
                                                LinearLayoutManager.VERTICAL,
                                                false);
                                        recyclerView.setLayoutManager(layout);
                                        throw new RuntimeException("FOda-se");
                                    } else {

                                    }
                                }

                                @Override
                                public void onFailure(Call<GitHubUser> call, Throwable t) {

                                }
                            });

                        }
                    } else {

                    }
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
        View view = inflater.inflate(R.layout.fragment_my_followings, container, false);
        ButterKnife.bind(this, view);
        this.context = getActivity().getApplicationContext();
        getFollowings();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FollowersFragment.OnFragmentInteractionListener) {
            mListener = (FollowersFragment.OnFragmentInteractionListener) context;
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