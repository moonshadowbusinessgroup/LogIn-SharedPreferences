package com.hadimusthafa.meridian;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    static final String KEY_USER_REGISTERED = "user";
    static final String KEY_EMAIL_REGISTERED = "eml";
    static final String KEY_PASS_REGISTERED = "pass";

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setRegisteredUser(Context context, String username) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_USER_REGISTERED, username);
        editor.apply();
    }

    public static void setRegisteredEmail(Context context, String email) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_EMAIL_REGISTERED, email);
        editor.apply();
    }

    public static String getRegisteredUser(Context context) {
        return getSharedPreference(context).getString(KEY_USER_REGISTERED, "");
    }

    public static String getRegisteredEmail(Context context) {
        return getSharedPreference(context).getString(KEY_EMAIL_REGISTERED, "");
    }

    public static void setRegisteredPass(Context context, String password) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(KEY_PASS_REGISTERED, password);
        editor.apply();
    }

    public static String getRegisteredPass(Context context) {
        return getSharedPreference(context).getString(KEY_PASS_REGISTERED, "");
    }

    public static void clearRegisteredUser(Context context) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USER_REGISTERED);
        editor.remove(KEY_PASS_REGISTERED);
        editor.apply();
    }
}
