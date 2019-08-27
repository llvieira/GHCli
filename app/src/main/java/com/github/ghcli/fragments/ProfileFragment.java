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
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ghcli.R;
import com.github.ghcli.adapter.ListOrganizationsAdapter;
import com.github.ghcli.models.GitHubOrganization;
import com.github.ghcli.models.GitHubUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static android.graphics.Bitmap.createBitmap;

public class ProfileFragment extends Fragment {
    private static final String ARG_USER = "user";
    private static final String ARG_USER_ORGANIZATIONS = "organizations";
    private static final String OVERVIEW_FOLLOWERS = "Followers: ";
    private static final String OVERVIEW_FOLLOWINGS = "Followings: ";
    private static final String OVERVIEW_PUBLIC_REPOS = "Public Repos: ";
    private static final String OVERVIEW_PRIVATE_REPOS = "Private Repos: ";


    private OnFragmentInteractionListener mListener;
    private GitHubUser user;
    private List<GitHubOrganization> userOrganizations;

    @BindView(R.id.profileImage) ImageView profileImage;
    @BindView(R.id.profileName) TextView profileName;
    @BindView(R.id.profileLogin) TextView profileLogin;
    @BindView(R.id.profileBio) TextView profileBio;
    @BindView(R.id.profileEmail) TextView profileEmail;
    @BindView(R.id.profileLocation) TextView profileLocation;
    @BindView(R.id.overviewFollowers) TextView overviewFollowers;
    @BindView(R.id.overviewFollowings) TextView overviewFollowings;
    @BindView(R.id.overviewPublicRepos) TextView overviewPublicRepos;
    @BindView(R.id.overviewPrivateRepos) TextView overviewPrivateRepos;
    @BindView(R.id.profileOrganizations) TextView profileOrganizations;
    @BindView(R.id.lineOrganizations) View lineOrganizations;
    @BindView(R.id.organizationsRecyclerView) RecyclerView organizationsRecyclerView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance(GitHubUser user, ArrayList<GitHubOrganization> userOrganizations) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        if (user != null) {
            args.putParcelable(ARG_USER, user);
        }

        if (userOrganizations != null) {
            args.putParcelableArrayList(ARG_USER_ORGANIZATIONS, userOrganizations);
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_USER);
            userOrganizations = getArguments().getParcelableArrayList(ARG_USER_ORGANIZATIONS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(user.getAvatarUrl()).transform(new CropCircleTransformation()).error(R.mipmap.octocat).into(profileImage);

        if(user.getName() != null) {
            profileName.setText(user.getName());
        }

        if(user.getLogin() != null) {
            profileLogin.setText(user.getLogin());
        }

        if(user.getBio() != null) {
            profileBio.setText(user.getBio());
        }

        if(user.getEmail() != null) {
            profileEmail.setText(user.getEmail());
        }

        if(user.getLocation() != null) {
            profileLocation.setText(user.getLocation());
        }

        if(user.getFollowers() != null) {
            overviewFollowers.setText(OVERVIEW_FOLLOWERS + user.getFollowers());
        }

        if(user.getFollowings() != null) {
            overviewFollowings.setText(OVERVIEW_FOLLOWINGS + user.getFollowings());
        }

        if(user.getPublicRepos() != null) {
            overviewPublicRepos.setText(OVERVIEW_PUBLIC_REPOS + user.getPublicRepos());
        }

        if(user.getPrivateRepos() != null) {
            overviewPrivateRepos.setText(OVERVIEW_PRIVATE_REPOS + user.getPrivateRepos());
        }

        if(userOrganizations != null && !userOrganizations.isEmpty()) {
            organizationsRecyclerView.setAdapter(new ListOrganizationsAdapter(userOrganizations));
            RecyclerView.LayoutManager layout = new LinearLayoutManager(
                    getActivity().getApplicationContext(),
                    LinearLayoutManager.VERTICAL,
                    false);
            organizationsRecyclerView.setLayoutManager(layout);
        } else {
            lineOrganizations.setVisibility(View.INVISIBLE);
            profileOrganizations.setVisibility(View.INVISIBLE);
            organizationsRecyclerView.setVisibility(View.INVISIBLE);
        }

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>***
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
