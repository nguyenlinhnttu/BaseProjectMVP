package com.base.android.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by cuonghc on 11/23/17.
 */

public class NetworkUtils {

    public static boolean checkNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo() == null || !connectivityManager.getActiveNetworkInfo().isConnected();
        } else
            return false;
    }
}
