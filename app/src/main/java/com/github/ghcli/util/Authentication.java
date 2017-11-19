package com.github.ghcli.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * This class is responsible for managing the user GitHub credentials.
 */
public class Authentication {

    private static final String TOKEN_KEY = "TOKEN";
    private static final String SHARED_PREFERENCES_NAME = "preferences";

    public static void saveToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);

        editor.apply();
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public static void removeToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);

        editor.apply();
    }
}
