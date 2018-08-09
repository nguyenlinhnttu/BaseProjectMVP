package com.basemvp.android.base;

import android.os.Bundle;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity{
    protected P mvpPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPresenter = createPresenter();
    }


    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null)
            mvpPresenter.detachView();
    }


}
