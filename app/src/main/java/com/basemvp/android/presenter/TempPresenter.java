package com.basemvp.android.presenter;

import com.basemvp.android.base.BasePresenter;
import com.basemvp.android.view.ITempView;

/**
 * Created by NguyenLinh on 08,August,2018
 */
public class TempPresenter extends BasePresenter<ITempView> {
    public TempPresenter(ITempView iTempView) {
        attachView(iTempView);
    }
}
