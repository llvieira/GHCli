package com.github.ghcli.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ghcli.R;
import com.github.ghcli.models.GitHubUser;
import com.github.ghcli.service.ServiceGenerator;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;
import com.github.ghcli.util.Message;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.graphics.Bitmap.createBitmap;

public class ProfileFragment extends Fragment {
    private static final String ARG_USER = "user";
    private static final String PATH_IMAGE_GIT = "https://assets-cdn.github.com/images/modules/open_graph/github-mark.png";

    private OnFragmentInteractionListener mListener;
    private GitHubUser user;

    @BindView(R.id.profileImage) ImageView profileImage;
    @BindView(R.id.profileName) TextView profileName;
    @BindView(R.id.profileLogin) TextView profileLogin;
    @BindView(R.id.profileBio) TextView profileBio;
    @BindView(R.id.imageGit) ImageView imageGit;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance(GitHubUser user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        if (user != null) {
            args.putParcelable(ARG_USER, user);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(user.getAvatarUrl()).transform(new CropCircleTransformation()).error(R.mipmap.octocat).into(profileImage);
        Picasso.with(view.getContext()).load(PATH_IMAGE_GIT).transform(new CropCircleTransformation()).error(R.mipmap.octocat).into(imageGit);
        profileName.setText(user.getName());
        profileLogin.setText(user.getLogin());
        profileBio.setText(user.getBio());

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
