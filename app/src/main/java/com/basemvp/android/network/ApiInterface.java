package com.basemvp.android.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by nguyenvanlinh on 6/19/18.
 * Project: BaseProject
 */
public interface ApiInterface {
    @FormUrlEncoded
    @POST("v1/Login.php")
    Observable<Response<ResponseBody>> loginAccount(@Field("mailAddress") String email, @Field("password") String password);

    @GET("v1/GetPrBanner.php")
    Observable<Response<ResponseBody>> getBanner();

    @GET("v1/GetQuestionsParentCategories.php")
    Observable<Response<ResponseBody>> getQuestionParentCategories();

    @GET("v1/GetQuestionsList.php")
    Observable<Response<ResponseBody>> getQuestionList();

    @GET("v1/GetQuestionsChildCategories.php")
    Observable<Response<ResponseBody>> getQuestionChildCategories();

    @FormUrlEncoded
    @POST("v1/RegisterAnswerStatus.php")
    Observable<Response<ResponseBody>> registerAnswerStatus(@Field("questionId") int questionId, @Field("answerStatus") boolean answerStatus);

    @POST("v1/Logout.php")
    Observable<Response<ResponseBody>> logout();

    @FormUrlEncoded
    @POST("v1/RegisterFavoriteState.php")
    Observable<Response<ResponseBody>> registerFavoriteState(@Field("questionId") int questionId, @Field("favoriteState") boolean favoriteState);
}
