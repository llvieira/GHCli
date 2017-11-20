package com.github.ghcli.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.github.ghcli.R;

/**
 * Created by antonio on 20/11/17.
 */

public class Menssages {

    public static void showSnackbar(String menssage, View view) {
        Snackbar snackbar = Snackbar
                .make(view, menssage, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    public static void showToast(String menssage, Context context){

        Toast.makeText(context, menssage, Toast.LENGTH_LONG).show();

    }

}
