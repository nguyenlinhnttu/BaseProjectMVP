package com.base.android.function.login;

import com.base.android.base.BasePresenter;
import com.base.android.network.DataManager;

/**
 * Created by NguyenLinh on 08,August,2018
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView iLoginView) {
        attachView(iLoginView);
    }
    public void login(String email, String password) {
        mvpView.showProgress();
        addSubscriptionResponseBody(DataManager.getInstall().loginAccount(email, password), response -> {
            mvpView.hideProgress();

        }, throwable -> {
            mvpView.hideProgress();

        });
    }
}
