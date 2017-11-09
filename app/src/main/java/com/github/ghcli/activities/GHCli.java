package com.github.ghcli.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alorma.github.sdk.bean.dto.response.Token;
import com.alorma.github.sdk.services.login.RequestTokenClient;
import com.alorma.github.sdk.services.user.GetAuthUserClient;
import com.github.ghcli.R;
import com.github.ghcli.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GithubAuthProvider;

import rx.Observer;

public class GHCli extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghcli);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = getFirebaseAuthResultHandler();
    }

    private void accessGithubLoginData(String accessToken){
        accessLoginData(
                "github",
                accessToken
        );
    }

    private void accessLoginData( String provider, String... tokens ){
        if( tokens != null
                && tokens.length > 0
                && tokens[0] != null ){

            AuthCredential credential = GithubAuthProvider.getCredential( tokens[0]);

            user.saveProviderSP(GHCli.this, provider);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()){
//                                showSnackbar("Login social falhou");
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                            FirebaseCrash.report( e );
                        }
                    });
        }
        else{
            mAuth.signOut();
        }
    }

    private FirebaseAuth.AuthStateListener getFirebaseAuthResultHandler(){
        FirebaseAuth.AuthStateListener callback = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser userFirebase = firebaseAuth.getCurrentUser();

                if( userFirebase == null ){
                    return;
                }

                if( user.getId() == null
                        && isNameOk(user, userFirebase) ){

                    user.setId(userFirebase.getUid());
                    user.setNameIfNull( userFirebase.getDisplayName() );
                    user.setEmailIfNull( userFirebase.getEmail() );
                    user.saveDB();
                }

//                callMainActivity();
            }
        };
        return callback;
    }

    private void verifyLogged(){
        if( mAuth.getCurrentUser() != null ){
//            callMainActivity();
        }
        else{
            mAuth.addAuthStateListener( mAuthListener );
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        verifyLogged();
    }

    private boolean isNameOk(User user, FirebaseUser firebaseUser){
        return(
                user.getName() != null
                        || firebaseUser.getDisplayName() != null
        );
    }

    public void sendLoginGithubData(View view) {
//        FirebaseCrash.log("LoginActivity:clickListener:button:sendLoginGithubData()");
        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority("github.com")
                .appendPath("login")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("client_id", getString(R.string.github_app_id))
                .appendQueryParameter("scope", "user,user:email")
                .build();

        WebView webView = new WebView(this);
        webView.loadUrl( uri.toString() );
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Uri uri = Uri.parse(url);

                if( uri.getQueryParameter("code") != null
                        && uri.getScheme() != null
                        && uri.getScheme().equalsIgnoreCase("https")) {

                    requestGitHubUserAccessToken(uri.getQueryParameter("code"));
                }

                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Login GitHub");
        alert.setView(webView);
        alert.show();
    }

    private void requestGitHubUserAccessToken(String code){
        RequestTokenClient requestTokenClient = new RequestTokenClient(
                code,
                getString(R.string.github_app_id),
                getString(R.string.github_app_secret),
                getString(R.string.github_app_url)
        );

        requestTokenClient
                .observable()
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
//                        FirebaseCrash.report( e );
//                        showSnackbar( e.getMessage() );
                    }

                    @Override
                    public void onNext(Token token) {
                        if( token.access_token != null ){
                            requestGitHubUserData(token.access_token);
                        }
                    }
                });
    }

    private void requestGitHubUserData(final String accessToken) {
        GetAuthUserClient getAuthUserClient = new GetAuthUserClient(accessToken);
        getAuthUserClient
                .observable()
                .subscribe(new Observer<com.alorma.github.sdk.bean.dto.response.User>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
//                        FirebaseCrash.report( e );
                    }

                    @Override
                    public void onNext(com.alorma.github.sdk.bean.dto.response.User user) {
                        GHCli.this.user.setName(user.name);
                        GHCli.this.user.setEmail(user.email);

                        accessGithubLoginData(accessToken);
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghcli, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
