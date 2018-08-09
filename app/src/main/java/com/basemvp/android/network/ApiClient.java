package com.basemvp.android.network;

import com.basemvp.android.BuildConfig;
import com.basemvp.android.common.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public class ApiClient {
    private static Retrofit retrofit = null;
    private static ApiInterface apiInterface = null;

    public static ApiInterface getClient(){
        if(retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG){
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            }
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            httpClient.addInterceptor(new HeaderInterceptor());
            httpClient.readTimeout(30, SECONDS)
                    .writeTimeout(30, SECONDS)
                    .connectTimeout(30, SECONDS);
            OkHttpClient client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HOST_API)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        if(apiInterface == null)
            apiInterface = retrofit.create(ApiInterface.class);

        return apiInterface;
    }
}
