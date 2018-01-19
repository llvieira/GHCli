package com.github.ghcli.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.github.ghcli.R;
import com.github.ghcli.models.GitHubOrganization;
import com.github.ghcli.models.GitHubUser;
import com.github.ghcli.service.ServiceGenerator;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;
import com.github.ghcli.util.Message;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener, FollowersFragment.OnFragmentInteractionListener, ReposFragment.OnFragmentInteractionListener {
    private static final String SELECTED_ITEM = "arg_selected_item";
    private static final String KEY_USER = "user";
    private static final String KEY_USER_ORGANIZATIONS = "organizations";

    private BottomNavigationView navBar;
    private int mSelectedItem;

    private GitHubUser user;
    private ArrayList<GitHubOrganization> userOrganizations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        navBar = (BottomNavigationView) findViewById(R.id.navigation);
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        // get user from login page
        Intent intent = getIntent();
        this.user = intent.getParcelableExtra(KEY_USER);
        this.userOrganizations = intent.getParcelableArrayListExtra(KEY_USER_ORGANIZATIONS);

        Log.d("TOKEN", Authentication.getToken(getApplicationContext()));

        MenuItem selectedItem;

        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = navBar.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = navBar.getMenu().getItem(2);
        }
        selectFragment(selectedItem);
    }

    private void selectFragment(MenuItem item) {
        Fragment frag = null;
        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.navbar_profile:
                frag = ProfileFragment.newInstance(user, userOrganizations);
                break;
            case R.id.navbar_repos:
                frag = ReposFragment.newInstance("teste1","teste2");
                break;
            case R.id.navbar_followers:
                frag = FollowersFragment.newInstance("teste1", "teste2");
                break;
        }

        // update selected item
        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i< navBar.getMenu().size(); i++) {
            MenuItem menuItem = navBar.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }


        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.activity_home_page, frag, frag.getTag());
            ft.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
    //heavily based on https://github.com/segunfamisa/bottom-navigation-demo/blob/master/app/src/main/java/com/segunfamisa/sample/bottomnav/MainActivity.java
}