package com.base.android.function.login;

import com.base.android.R;
import com.base.android.base.BaseMvpFragment;

/**
 * Created by NguyenLinh on 15,January,2019
 */
public class LoginFragment extends BaseMvpFragment<LoginPresenter> implements ILoginView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void setUpData() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

}
