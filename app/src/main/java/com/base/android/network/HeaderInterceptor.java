package com.base.android.network;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.base.android.common.utils.LogUtils;
import com.base.android.common.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (!TextUtils.isEmpty(Constants.ACCESS_TOKEN)) {
            LogUtils.log("ACCESS_TOKEN: ",Constants.ACCESS_TOKEN);
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + Constants.ACCESS_TOKEN)
                    .build();
        }
        return chain.proceed(request);
    }
}