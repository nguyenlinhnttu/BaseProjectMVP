package com.base.android.network;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by tonkhanh on 8/23/17.
 */

public class DataManager extends ApiClient {
    private static DataManager mDataManager;
    public static DataManager getInstall() {
        if (mDataManager == null)
            mDataManager = new DataManager();
        return mDataManager;
    }

    public Observable<Response<ResponseBody>> loginAccount(String mail, String pass) {
        return getClient().loginAccount(mail, pass);
    }

}
