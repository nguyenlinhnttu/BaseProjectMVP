package com.basemvp.android.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPresenter = createPresenter();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null)
            mvpPresenter.detachView();
    }
}
