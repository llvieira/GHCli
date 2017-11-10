package com.github.ghcli.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.ghcli.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GHCli extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private boolean signed;
    private WebView webView;

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghcli);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    signed = true;
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                } else {
                    signed = false;
                    signInOut();
                }
            }
        };

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://ghcli-7ee0f.")) {
                    Uri uri = Uri.parse(url);
                    String code = uri.getQueryParameter("code");
                    String state = uri.getQueryParameter("state");
                    sendPost(code, state);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(getString(R.string.github_app_url))) {
            String code = uri.getQueryParameter("code");
            String state = uri.getQueryParameter("state");
            if (code != null && state != null)
                sendPost(code, state);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void signInOut() {
        if (!signed) {
            Uri uri = new Uri.Builder()
                    .scheme("http")
                    .authority("github.com")
                    .appendPath("login")
                    .appendPath("oauth")
                    .appendPath("authorize")
                    .appendQueryParameter("client_id", getString(R.string.github_app_id))
                    .appendQueryParameter("redirect_uri", getString(R.string.github_app_url))
                    .appendQueryParameter("scope", "user,user:email")
                    .build();

            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(uri.toString());

        } else {
            mAuth.signOut();
        }
    }

    private void sendPost(String code, String state) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody form = new FormBody.Builder()
                .add("client_id", getString(R.string.github_app_id))
                .add("client_secret", getString(R.string.github_app_secret))
                .add("code", code)
                .add("redirect_uri", getString(R.string.github_app_url))
                .build();

        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(form)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Toast.makeText(GHCli.this, "onFailure: " + e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                String[] splitted = responseBody.split("=|&");
                if (splitted[0].equalsIgnoreCase("access_token"))
                    signInWithToken(splitted[1]);
                else
                    Toast.makeText(GHCli.this, "splitted[0] =>" + splitted[0], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signInWithToken(String token) {

        AuthCredential credential = GithubAuthProvider.getCredential(token);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            task.getException().printStackTrace();
                            Toast.makeText(GHCli.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
