package com.base.android.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nguyenvanlinh on 6/19/18.
 * Project: BaseProject
 */
public class MySharePref {
    private static final String SHARED_NAME = "BASE_PROJECT";
    private static final String WALK_THOUGH = "SHOW_WALK_THOUGH";
    private static final String FLAG_LOGIN = "FLAG_LOGIN";
    private static final String USER_TOKEN = "USER_TOKEN";
    private static MySharePref mInstance;
    private SharedPreferences sharedPref;

    private MySharePref(Context context) {
        sharedPref = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized MySharePref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySharePref(context);
        }
        return mInstance;
    }

    public void saveFlagWalkThough() {
        sharedPref.edit().putBoolean(WALK_THOUGH, true).apply();
    }

    public boolean checkFlagWalkThough() {
        return sharedPref.getBoolean(WALK_THOUGH, false);
    }

    public void saveFlagLogin(boolean value) {
        sharedPref.edit().putBoolean(FLAG_LOGIN, value).apply();
    }

    public boolean checkFlagLogin() {
        return sharedPref.getBoolean(FLAG_LOGIN, false);
    }

    public void saveUserToken(String token) {
        sharedPref.edit().putString(USER_TOKEN, token).apply();
    }

    public String getUserToken() {
        return sharedPref.getString(USER_TOKEN, "");
    }

}
