package com.base.android.base;

import com.base.android.network.BaseResponse;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public interface BaseView {
    void showProgress();
    void hideProgress();
    void onRequestError(BaseResponse response);
    void onRequestFailure(Throwable throwable);
}
