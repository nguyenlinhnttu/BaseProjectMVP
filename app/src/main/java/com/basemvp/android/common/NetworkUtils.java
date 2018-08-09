package com.basemvp.android.common;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by cuonghc on 11/23/17.
 */

public class NetworkUtils {

    public static boolean checkNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
