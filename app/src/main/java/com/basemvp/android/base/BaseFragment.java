package com.basemvp.android.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basemvp.android.R;
import com.basemvp.android.common.AppLog;
import com.basemvp.android.common.DialogUtils;
import com.basemvp.android.network.BaseResponse;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    protected BaseActivity parentActivity;
    CompositeDisposable disposable;
    protected Boolean mIsBackPress = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            parentActivity = (BaseActivity) context;
        }
    }

    @Override
    final public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = setViewContent(inflater, getLayoutId(), container);
        ButterKnife.bind(this, v);
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpData();
    }

    private View setViewContent(LayoutInflater inflater, int resLayout, ViewGroup container) {
        return inflater.inflate(resLayout, null, false);

    }

    abstract protected int getLayoutId();

    abstract protected void setUpData();

    @Override
    public void onStart() {
        super.onStart();
        AppLog.log(getClass().getSimpleName(), "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        AppLog.log(getClass().getSimpleName(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        AppLog.log(getClass().getSimpleName(), "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        AppLog.log(getClass().getSimpleName(), "onStop");
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppLog.log(getClass().getSimpleName(), "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppLog.log(getClass().getSimpleName(), "onDestroyView");
    }

    @Override
    public void showProgress() {
        showLoadingDialog(true);
    }

    @Override
    public void showProgressNonCancel() {
        showLoadingDialog(false);
    }

    @Override
    public void hideProgress() {
        hideLoadingDialog();
    }

    @Override
    public void onRequestError(BaseResponse response) {
        DialogUtils.showDialogError(parentActivity, response);
    }

    @Override
    public void onRequestFailure(Throwable throwable) {
        DialogUtils.showDialogFailureRequest(parentActivity, throwable);
    }

    private ProgressDialog progressDialog;

    public void showLoadingDialog(boolean hasCancel) {
        if (isVisible()) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(hasCancel);
                progressDialog.setMessage(getString(R.string.loading));
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            }
            if (!progressDialog.isShowing()) {
                AppLog.log(getClass().getSimpleName(), "show Progress");
                try {
                    progressDialog.show();
                } catch (Exception ex) {
                }
            }
        }
    }

    public void hideLoadingDialog() {
        if (isVisible()) {
            if (progressDialog != null && progressDialog.isShowing()) {
                AppLog.log(getClass().getSimpleName(), "dismiss Progress");
                progressDialog.dismiss();
            }
        }
    }

    public Boolean onBackPressed() {
        return mIsBackPress;
    }

    public void internetRequest() {
        DialogUtils.showDialogMessage(parentActivity, getString(R.string.msg_request_no_internet));
    }
}
