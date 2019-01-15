package com.base.android.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by nguyenvanlinh on 6/19/18.
 * Project: BaseProject
 */
public interface ApiInterface {
    @FormUrlEncoded
    @POST("v1/Login.php")
    Observable<Response<ResponseBody>> loginAccount(@Field("mailAddress") String email, @Field("password") String password);
}
