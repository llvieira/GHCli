package com.github.ghcli.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.LinearLayout;

import com.github.ghcli.R;

/**
 * Created by antonio on 20/11/17.
 */

public class Connection {

    private static final String TURN_ON_WIFI = "Turn on WIFI";
    private static final String NO_CONNECTION = "No internet connection";

    static public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    static public void snackbarWifi(final View v, final Context context){

        Snackbar.make(v, NO_CONNECTION, Snackbar.LENGTH_INDEFINITE).setAction(TURN_ON_WIFI, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);

                LinearLayout linearLayout = v.findViewById(R.id.layoutErrorConnection);
                linearLayout.setVisibility(View.VISIBLE);



            }
        }).show();
    }


}
