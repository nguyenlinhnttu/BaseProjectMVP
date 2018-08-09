package com.basemvp.android.network;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by tonkhanh on 8/23/17.
 */

public class DataManager {
    private static ApiInterface mApiStores;
    private static DataManager mDataManager;

    public static DataManager getInstall() {
        if (mDataManager == null) {
            mDataManager = new DataManager();
        }
        if (mApiStores == null) {
            mApiStores = ApiClient.getClient();
        }
        return mDataManager;
    }

    public Observable<Response<ResponseBody>> loginAccount(String mail, String pass) {
        return mApiStores.loginAccount(mail, pass);
    }

    public Observable<Response<ResponseBody>> getBanner() {
        return mApiStores.getBanner();
    }

    public Observable<Response<ResponseBody>> getQuestionParentCategories() {
        return mApiStores.getQuestionParentCategories();
    }

    public Observable<Response<ResponseBody>> getQuestionChildCategories() {
        return mApiStores.getQuestionChildCategories();
    }

    public Observable<Response<ResponseBody>> getQuestionList() {
        return mApiStores.getQuestionList();
    }

    public Observable<Response<ResponseBody>> registerAnswerStatus(int questionId, boolean answerStatus) {
        return mApiStores.registerAnswerStatus(questionId,answerStatus);
    }

    public Observable<Response<ResponseBody>> logout() {
        return mApiStores.logout();
    }

    public Observable<Response<ResponseBody>> registerFavoriteStatus(int questionId, boolean status) {
        return mApiStores.registerFavoriteState(questionId,status);
    }
}
