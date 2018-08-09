package com.basemvp.android.network;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.basemvp.android.common.AppLog;
import com.basemvp.android.common.Constants;

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
        if (Constants.ACCESS_TOKEN != null && !TextUtils.isEmpty(Constants.ACCESS_TOKEN)) {
            AppLog.log("ACCESS_TOKEN: ",Constants.ACCESS_TOKEN);
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + Constants.ACCESS_TOKEN)
                    .build();
        }
        return chain.proceed(request);
    }
}