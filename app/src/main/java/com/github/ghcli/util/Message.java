package com.github.ghcli.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public class Message {

    public static void showSnackbar(String message, View view) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void showToast(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
