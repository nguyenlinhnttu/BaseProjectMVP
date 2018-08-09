package com.basemvp.android.base;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.basemvp.android.R;
import com.basemvp.android.common.Constants;
import com.basemvp.android.common.DialogUtils;
import com.basemvp.android.common.FragmentUtil;
import com.basemvp.android.common.MySharePreferences;
import com.basemvp.android.customview.CustomTextView;
import com.basemvp.android.network.BaseResponse;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected String TAG = getClass().getSimpleName();
    private Class<?> mClass;
    private static final int CAMERA_PERMISSION = 1;
    public CompositeDisposable disposable;
    protected FragmentManager mFragmentManager;
    protected FragmentUtil mFragmentUtil;
    public int viewContainer = android.R.id.content;
    public MySharePreferences mySharePreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());
        setContentView(getLayoutId());
        mySharePreferences = MySharePreferences.getInstance(this);
        Constants.ACCESS_TOKEN = mySharePreferences.getUserToken();
        mFragmentManager = getSupportFragmentManager();
        mFragmentUtil = FragmentUtil.getInstance();
        overridePendingTransition(R.anim.slide_right_in, R.anim.stay);
        ButterKnife.bind(this);
        setUpData();
    }


    abstract protected void setUpData();

    abstract protected int getLayoutId();

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.stay, R.anim.slide_right_out);
    }


    @Override
    public void showProgressNonCancel() {
        showLoading(false);
    }

    @Override
    public void showProgress() {
        showLoading(true);
    }

    @Override
    public void hideProgress() {
        hideLoading();
    }

    @Override
    public void onRequestError(BaseResponse response) {
        DialogUtils.showDialogError(this, response);
    }

    @Override
    public void onRequestFailure(Throwable throwable) {
        DialogUtils.showDialogFailureRequest(this, throwable);
    }

    public void internetRequest(){
        DialogUtils.showDialogMessage(this, getString(R.string.msg_request_no_internet));
    }

    protected void setupToolbar(String title, boolean enableBackButton) {
        try {
            setSupportActionBar(findViewById(R.id.my_toolbar));
            if (getSupportActionBar() != null) {
                // getSupportActionBar().setDisplayShowTitleEnabled(false);
                CustomTextView txtTitle = findViewById(R.id.tv_title_name);
                AppCompatImageButton btnBack = findViewById(R.id.btn_back);
//                View viewLine = findViewById(R.id.view_line);
//                if (android.os.Build.VERSION.SDK_INT >= 21) {
//                    viewLine.setVisibility(View.GONE);
//                }
                if (enableBackButton) {
                    btnBack.setVisibility(View.VISIBLE);
                    btnBack.setOnClickListener(view -> {
                        onBackPressed();
                    });
                } else {
                    btnBack.setVisibility(View.GONE);
                }
                txtTitle.setText(title);
            }
        } catch (Exception e) {
            Log.e(TAG + "setupToolbar", e.getMessage());
        }
    }

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClass = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (mClass != null) {
                        Intent intent = new Intent(this, mClass);
                        startActivity(intent);
                    }
                } else {
                    DialogUtils.showDialogMessage(this, "Please grant permission to use App.");
                }
                return;
        }
    }

    public Dialog mProgressDialog;

    public void showLoading(boolean isCancel) {

        if (mProgressDialog == null) {
            mProgressDialog = new Dialog(this);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setCancelable(isCancel);
            mProgressDialog.setContentView(R.layout.dialog_loading);
            if (mProgressDialog.getWindow() != null && !isFinishing()) {
                mProgressDialog.getWindow().setBackgroundDrawableResource(
                        R.color.transparent);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;

                if (!mProgressDialog.isShowing())
                    mProgressDialog.show();
                mProgressDialog.getWindow().setAttributes(lp);
            }
        } else {
            if (!isFinishing() && !mProgressDialog.isShowing())
                mProgressDialog.show();
        }
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void replaceFragment(BaseFragment fragment) {
        mFragmentUtil.replaceFragment(mFragmentManager, fragment, viewContainer);
    }

    public void replaceFragmentWithoutAdToBackStack(BaseFragment fragment) {
        mFragmentUtil.replaceFragmentWithoutAdToBackStack(mFragmentManager, fragment, viewContainer);
    }

    public void addFragment(BaseFragment fragment) {
        mFragmentUtil.addFragment(mFragmentManager, fragment, viewContainer);
    }

    public void backToMainFragment() {
        mFragmentUtil.backStackToMain(mFragmentManager);
    }

    public void replaceFragmentAfterResetBackstack(BaseFragment fragment) {
        mFragmentUtil.replaceFragmentAfterResetBackstack(mFragmentManager, fragment, viewContainer);
    }

}
