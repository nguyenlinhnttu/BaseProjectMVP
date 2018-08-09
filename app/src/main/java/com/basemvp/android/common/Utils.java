package com.basemvp.android.common;

import android.animation.ValueAnimator;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.TextView;

public class Utils {
    public static void animationTextCount(int value, TextView tvView, long time) {
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, value);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                tvView.setText(String.valueOf(animation.getAnimatedValue()));
            }
        });
        animator.setDuration(time);
        animator.start();
    }

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
