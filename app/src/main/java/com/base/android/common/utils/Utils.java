package com.base.android.common.utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.base.android.common.Constants;
import com.base.android.function.MainActivity;

import static android.content.Context.INPUT_METHOD_SERVICE;

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

    public static void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void hideKeyBoard(Activity mActivity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {

        }
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int convertDpToPixel(int dp) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);

        return displaymetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);

        return displaymetrics.heightPixels;
    }

    public static String getUUID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    private static boolean checkAppInstall(final Context mContext, final String packageName) {
        PackageManager pm = mContext.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void rateAppInPlayStore(Context mContext) {
        Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            mContext.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + mContext.getPackageName())));
        }
    }

    public static void shareEmail(Context mContext, String subject, String body) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:")); // + Email if Exits
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        mContext.startActivity(Intent.createChooser(emailIntent, ""));
    }

    public static void shareText(Context mContext, String body) {
        Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
        txtIntent.setType("text/plain");
        txtIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
        mContext.startActivity(Intent.createChooser(txtIntent, ""));
    }

    public static void showHTMLTextView(TextView tvView, String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvView.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvView.setText(Html.fromHtml(html));
        }
    }

    public static String convertHTMLTextView(String html) {
        String text = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            text = Html.fromHtml(html).toString();
        }
        return text;
    }


    public static boolean checkSDKPreLollipop() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }

    public static void createChancelNotification(Context mContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel androidChannel = new NotificationChannel(Constants.ANDROID_CHANNEL_ID, Constants.ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(androidChannel);
        }
    }

    public static boolean checkFragmentShowing(FragmentManager fragmentManager, String tag) {
        Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
        return prevFragment != null;

    }

    public static Drawable getDrawable(Context mContext, int resouce) {
        return ContextCompat.getDrawable(mContext, resouce);
    }

    public static void makeFullTranspatentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(activity, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(activity, false);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private static void setWindowFlag(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        } else {
            winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        }
        win.setAttributes(winParams);
    }

    public static void restartApp(Activity mActivity) {
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mActivity.startActivity(intent);
        mActivity.finish();
    }

}
