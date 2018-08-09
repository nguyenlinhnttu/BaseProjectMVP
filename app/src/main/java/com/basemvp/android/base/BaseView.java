package com.basemvp.android.base;

import com.basemvp.android.network.BaseResponse;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public interface BaseView {
    void showProgress();
    void showProgressNonCancel();
    void hideProgress();
    void onRequestError(BaseResponse response);
    void onRequestFailure(Throwable throwable);
}
