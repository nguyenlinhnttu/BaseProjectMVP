package com.base.android.network;

import com.base.android.BuildConfig;
import com.base.android.common.Constants;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
    private static Dispatcher mDispatcher = new Dispatcher();
    public static final int TIME_OUT_READ = 30;
    public static final int TIME_OUT_WRITE = 60;

    static ApiInterface getClient(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HOST_API)
                    .client(getUnsafeOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        if(apiInterface == null)
            apiInterface = retrofit.create(ApiInterface.class);

        return apiInterface;
    }

    public void cancelAllRequest(){
        mDispatcher.cancelAll();
    }

    public static RequestBody createRequest(String request) {
        return RequestBody.create(MediaType.parse("application/json"), request);
    }

    public static OkHttpClient getUnsafeOkHttpClient() {

        try {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder
                    .addNetworkInterceptor(new HeaderInterceptor())
                    .addInterceptor(interceptor)
                    .connectTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
                    .writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .dispatcher(mDispatcher)
                    .build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
