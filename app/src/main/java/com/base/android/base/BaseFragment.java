package com.base.android.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.android.R;
import com.base.android.common.utils.LogUtils;
import com.base.android.common.utils.DialogUtils;
import com.base.android.network.BaseResponse;

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
        LogUtils.log(getClass().getSimpleName(), "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.log(getClass().getSimpleName(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.log(getClass().getSimpleName(), "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.log(getClass().getSimpleName(), "onStop");
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.log(getClass().getSimpleName(), "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.log(getClass().getSimpleName(), "onDestroyView");
    }

    @Override
    public void showProgress() {
        showLoadingDialog(true);
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
                LogUtils.log(getClass().getSimpleName(), "show Progress");
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
                LogUtils.log(getClass().getSimpleName(), "dismiss Progress");
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
