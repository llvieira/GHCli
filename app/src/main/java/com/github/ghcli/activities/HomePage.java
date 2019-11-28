package com.github.ghcli.activities;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.ghcli.R;
import com.github.ghcli.fragments.FollowersFragment;
import com.github.ghcli.fragments.IssuesFragment;
import com.github.ghcli.fragments.MyFollowingFragment;
import com.github.ghcli.fragments.NotificationsFragment;
import com.github.ghcli.fragments.ProfileFragment;
import com.github.ghcli.fragments.PullRequesFragment;
import com.github.ghcli.fragments.ReposFragment;
import com.github.ghcli.fragments.StarredReposFragment;
import com.github.ghcli.models.GitHubOrganization;
import com.github.ghcli.models.GitHubUser;
import com.github.ghcli.models.SocketIOUser;
import com.github.ghcli.service.ServiceGenerator;
import com.github.ghcli.service.clients.IGitHubUser;
import com.github.ghcli.util.Authentication;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity implements
        ProfileFragment.OnFragmentInteractionListener,
        NotificationsFragment.OnFragmentInteractionListener,
        FollowersFragment.OnFragmentInteractionListener,
        ReposFragment.OnFragmentInteractionListener,
        StarredReposFragment.OnFragmentInteractionListener,
        IssuesFragment.OnFragmentInteractionListener,
        PullRequesFragment.OnFragmentInteractionListener {

    private static final String COLOR_ACTION_ITEM = "#444444";
    private static final String COLOR_BACKGROUND_LISTVIEW = "#111111";
    private static final String KEY_USER = "user";
    private static final String KEY_USER_ORGANIZATIONS = "organizations";

    private DrawerLayout drawerLayout;
    private String[] actions = {"Profile", "Notifications", "Repositories", "Stars", "Followers", "Issues", "Pull Requests", "Following",  "Sign out"};
    private ListView leftDrawer;
    private ActionBarDrawerToggle drawerToggle;

    private GitHubUser user;
    private ArrayList<GitHubOrganization> userOrganizations;

    private static String CHANNEL_ID = "channel_1";
    private static String INTENT_EXTRA = "fromNotification";

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://191.252.196.48:8080");
            Log.d("socket", mSocket.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createNotificationChannel();

        // connecting to socket.io
        mSocket.connect();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_home_page);
        drawerLayout = findViewById(R.id.activity_home_page);
        leftDrawer = findViewById(R.id.left_drawer);

        // get user from login page
        Intent intent = getIntent();
        this.user = intent.getParcelableExtra(KEY_USER);
        this.userOrganizations = intent.getParcelableArrayListExtra(KEY_USER_ORGANIZATIONS);

        Log.d("TOKEN", Authentication.getToken(getApplicationContext()));

        // emitting message to socket.io
        SocketIOUser socketIOUser = new SocketIOUser(
                Authentication.getToken(getApplicationContext()).split(" ")[1],
                user.getEmail());
        Gson gson = new Gson();
        String socketIOUserJson = gson.toJson(socketIOUser);
        mSocket.emit("message", socketIOUserJson);

        mSocket.on("notification", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("Socket IO", args[0].toString());

                        try {
                            JSONObject notification = new JSONObject(args[0].toString());
                            showNotification(notification.getString("reason"),
                                    notification.getInt("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        leftDrawer.setAdapter(new ArrayAdapter<>(
                this,
                R.layout.actions_list_drawer,
                actions));
        leftDrawer.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                selectFragment(position);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                leftDrawer.bringToFront();
                drawerLayout.requestLayout();
                invalidateOptionsMenu();
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        selectFragment(0);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra(INTENT_EXTRA)) {
            markNotificationAsRead();
        }
    }

    private void markNotificationAsRead() {
        String credentials = Authentication.getToken(getApplicationContext());
        final IGitHubUser gitHubUserClient = ServiceGenerator.createService(IGitHubUser.class);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = dateFormat.format(new Date());

        HashMap<String, String> lastRead = new HashMap<>();
        lastRead.put("last_read_at", date);
        Call<Void> call = gitHubUserClient.markNotificationAsRead(credentials, lastRead);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call,
                                   @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("notification", "Notification read!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("notification", "Notification not read!");
            }
        });
    }

    private void showNotification(String text, int notificationId) {
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra(INTENT_EXTRA, true);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_eye)
                .setContentTitle("GHCli Notification")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectFragment(int item) {
        Fragment frag = null;
        // init corresponding fragment
        switch (item) {
            case 0:
                frag = ProfileFragment.newInstance(user, userOrganizations);
                break;
            case 1:
                frag = NotificationsFragment.newInstance("teste1","teste2");
                break;
            case 2:
                frag = ReposFragment.newInstance("teste1","teste2");
                break;
            case 3:
                frag = StarredReposFragment.newInstance("teste1", "teste2");
                break;
            case 4:
                frag = FollowersFragment.newInstance("teste1", "teste2");
                break;
            case 5:
                frag = IssuesFragment.newInstance("teste1","teste2");
                break;
            case 6:
                frag = PullRequesFragment.newInstance("teste1", "teste2");
                break;
            case 7:
                frag = MyFollowingFragment.newInstance("teste1", "teste2");
                break;
            case 8:
                logout();
                break;
        }

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.activity_home_page, frag, frag.getTag());
            ft.commit();
        }

        leftDrawer.setItemChecked(item, true);
        setColor(item);
        drawerLayout.closeDrawer(leftDrawer);
        getSupportActionBar().setTitle(actions[item]);
    }

    @SuppressLint("ResourceAsColor")
    private void setColor(int position) {
        for (int i = 0; i < leftDrawer.getChildCount(); i++) {
            if (i == position) {
                leftDrawer.getChildAt(i).setBackgroundColor(Color.parseColor(COLOR_ACTION_ITEM));
            } else {
                leftDrawer.getChildAt(i).setBackgroundColor(Color.parseColor(COLOR_BACKGROUND_LISTVIEW));
            }
        }
    }

    public void logout() {
        Authentication.removeToken(getApplicationContext());
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
