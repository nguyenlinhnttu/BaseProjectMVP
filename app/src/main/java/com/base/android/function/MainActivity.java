package com.base.android.function;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.base.android.R;
import com.base.android.base.BaseActivity;
import com.base.android.function.login.LoginFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewContainer = R.id.container;
        replaceFragmentFirst(new LoginFragment());
    }

    @Override
    protected void setUpData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
