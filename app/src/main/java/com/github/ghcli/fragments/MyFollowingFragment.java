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

import com.github.ghcli.R;
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

public class MyFollowingFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rvListMyFollowings)
    RecyclerView recyclerView;

    private String mParam1;
    private String mParam2;

    private FollowersFragment.OnFragmentInteractionListener mListener;
    private IGitHubUser iGitHubUser;
    private Context context;



    public MyFollowingFragment() {

    }

    public static MyFollowingFragment newInstance(String param1, String param2) {
        MyFollowingFragment fragment = new MyFollowingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        fragment.setiGitHubUser(ServiceGenerator.createService(IGitHubUser.class));
        return fragment;
    }

    private void getFollowings() {
        Call<List<GitHubUser>> callFollowers = iGitHubUser.getFollowing(Authentication.getToken(context));
        List<GitHubUser> gitHubUsers = new ArrayList<>();
        List<GitHubUser> myFollowers = new ArrayList<>();
        try {
            gitHubUsers = callFollowers.execute().body();

            for(GitHubUser gitHubUser : gitHubUsers){

                Call<GitHubUser> callMyFollowing = iGitHubUser.getOneUser(Authentication.getToken(context), gitHubUser.getLogin());
                myFollowers.add(callMyFollowing.execute().body());

            }
            recyclerView.setAdapter(new ListMyFollowingAdapter(context, myFollowers,iGitHubUser));
            RecyclerView.LayoutManager layout = new LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false);
            recyclerView.setLayoutManager(layout);


        } catch (IOException e) {
            e.printStackTrace();
        }


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