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
import com.github.ghcli.adapter.ListNotificationsAdapter;
import com.github.ghcli.models.GitHubNotification;
import com.github.ghcli.service.ServiceGenerator;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.notificationsProgressBar) ProgressBar notificationsProgressBar;
    @BindView(R.id.notificationsRecyclerView) RecyclerView notificationsRecyclerView;


    public NotificationsFragment() {
        // Required empty public constructor
    }

    public static NotificationsFragment newInstance(String param1, String param2) {
        NotificationsFragment fragment = new NotificationsFragment();
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

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        ButterKnife.bind(this, view);

        final IGitHubUser gitHubUserClient = ServiceGenerator.createService(IGitHubUser.class);
        loadNotifications(gitHubUserClient);
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

    private void loadNotifications(IGitHubUser gitHubUserClient) {
        Call<List<GitHubNotification>> callNotifications = gitHubUserClient.getNotifications(Authentication.getToken(getActivity().getApplicationContext()));
        notificationsProgressBar.setVisibility(View.VISIBLE);
        callNotifications.enqueue(new Callback<List<GitHubNotification>>() {
            @Override
            public void onResponse(Call<List<GitHubNotification>> call, Response<List<GitHubNotification>> response) {
                if(response.isSuccessful()) {
                    notificationsProgressBar.setVisibility(View.INVISIBLE);
                    List<GitHubNotification> notifications = response.body();
                    notificationsRecyclerView.setAdapter(new ListNotificationsAdapter(notifications));
                    RecyclerView.LayoutManager layout = new LinearLayoutManager(
                            getActivity().getApplicationContext(),
                            LinearLayoutManager.VERTICAL,
                            false);
                    notificationsRecyclerView.setLayoutManager(layout);
                }
            }

            @Override
            public void onFailure(Call<List<GitHubNotification>> call, Throwable t) {

            }
        });
    }
}
