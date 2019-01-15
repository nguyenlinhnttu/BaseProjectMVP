package com.base.android.customs;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by nguyenvanlinh on 3/2/18.
 * Project: BaseProject
 */

public class MyWebView extends WebView {


    public MyWebView(Context context) {
        super(context);
        initView();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        this.setWebChromeClient(new WebChromeClient());
        this.setWebViewClient(new WebViewClient());
        this.getSettings().setDomStorageEnabled(true);
        this.getSettings().setLoadsImagesAutomatically(true);
        this.getSettings().setJavaScriptEnabled(true);
        this.getSettings().setLoadWithOverviewMode(true);
        this.getSettings().setUseWideViewPort(true);
        this.clearFormData();
        this.clearCache(true);
        this.getSettings().setAppCacheEnabled(false);
        this.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        this.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

}

