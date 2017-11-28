package com.github.ghcli.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.github.ghcli.R;
import com.github.ghcli.models.GitHubUser;
import com.github.ghcli.service.ServiceGenerator;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_FAILED = "Login failed. Please try again!";

    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        String credentials = Authentication.getToken(getApplicationContext());

        // If credentials already exists, go to home page
        if (credentials != null) {
            startActivity(new Intent(getApplicationContext(), HomePage.class));
            finish();
        }
    }

    @OnClick(R.id.sign_in)
    public void login() {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        final String credentials = Credentials.basic(email, password);

        IGitHubUser gitHubUserClient = ServiceGenerator.createService(IGitHubUser.class);
        Call<GitHubUser> call = gitHubUserClient.getUser(credentials);

        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(@NonNull Call<GitHubUser> call,
                                   @NonNull Response<GitHubUser> response) {
                // If username and password are valid information
                // save the token and go to home page
                if (response.isSuccessful()) {
                    Authentication.saveToken(getApplicationContext(), credentials);

                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    finish();
                } else {
                    showSnackbar();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GitHubUser> call, @NonNull Throwable t) {
                showSnackbar();
            }
        });
    }

    @OnClick(R.id.close)
    public void closeApp() {
        finish();
    }

    private void showSnackbar() {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.content_login), LOGIN_FAILED, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
