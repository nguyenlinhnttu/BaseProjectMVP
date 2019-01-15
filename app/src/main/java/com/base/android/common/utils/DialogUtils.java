package com.base.android.common.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.base.android.R;
import com.base.android.customs.CustomTextView;
import com.base.android.network.BaseResponse;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public class DialogUtils {
    private static Dialog dialogNotify;
    private static boolean isDialogShowing = false;

    /**
     * Dialog show error when request api
     * @param errorResponse
     */

    public static void showDialogError(final Context context, final BaseResponse errorResponse) {
        if (dialogNotify == null || ((ContextThemeWrapper) dialogNotify.getContext()).getBaseContext() != context) {
            dialogNotify = new Dialog(context);
            dialogNotify.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogNotify.setCancelable(true);
            dialogNotify.setCanceledOnTouchOutside(false);
            dialogNotify.getWindow().setGravity(Gravity.CENTER);
            dialogNotify.setContentView(R.layout.dialog_notify);
            dialogNotify.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            CustomTextView tvMessage = dialogNotify.findViewById(R.id.tv_message);
            String message = context.getString(R.string.msg_request_default_error);
            if (errorResponse.getDialogMessage() == null || errorResponse.getDialogMessage().equals("")) {
                tvMessage.setText(message);
            } else {
                tvMessage.setText(errorResponse.getDialogMessage());
            }

            CustomTextView btnOK = dialogNotify.findViewById(R.id.btn_ok);
            btnOK.setOnClickListener(view -> dialogNotify.dismiss());

        }
        dialogNotify.setOnDismissListener(dialogInterface -> isDialogShowing = false);
        if (!dialogNotify.isShowing() && !isDialogShowing) {
            dialogNotify.show();
            isDialogShowing = true;
        }
    }

    /**
     * Dialog show failure when request api
     */
    public static void showDialogFailureRequest(final Context context, Throwable t) {
        if (t == null) {
            return;
        }
        if (dialogNotify == null || ((ContextThemeWrapper) dialogNotify.getContext()).getBaseContext() != context) {
            dialogNotify = new Dialog(context);
            dialogNotify.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogNotify.setCancelable(true);
            dialogNotify.setCanceledOnTouchOutside(false);
            dialogNotify.getWindow().setGravity(Gravity.CENTER);
            dialogNotify.setContentView(R.layout.dialog_notify);
            dialogNotify.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            CustomTextView tvMessage = dialogNotify.findViewById(R.id.tv_message);
            String message = context.getString(R.string.msg_request_default_error);
            if (t instanceof SocketTimeoutException) {
                message = context.getString(R.string.msg_request_timeout);
            } else if (t instanceof IOException) {
                message = context.getString(R.string.msg_request_no_internet);
            }
            tvMessage.setText(message);
            CustomTextView btnOK = dialogNotify.findViewById(R.id.btn_ok);
            btnOK.setOnClickListener(view -> dialogNotify.dismiss());

        }
        dialogNotify.setOnDismissListener(dialogInterface -> isDialogShowing = false);
        if (!dialogNotify.isShowing() && !isDialogShowing) {
            dialogNotify.show();
            isDialogShowing = true;
        }
    }

    /**
     * showDialogMessage
     */
    public static void showDialogMessage(final Context context, final String message) {
        if (dialogNotify == null || ((ContextThemeWrapper) dialogNotify.getContext()).getBaseContext() != context) {
            dialogNotify = new Dialog(context);
            dialogNotify.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogNotify.setCancelable(true);
            dialogNotify.setCanceledOnTouchOutside(false);
            dialogNotify.getWindow().setGravity(Gravity.CENTER);
            dialogNotify.setContentView(R.layout.dialog_notify);
            dialogNotify.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            CustomTextView tvMessage = dialogNotify.findViewById(R.id.tv_message);
            tvMessage.setText(message);
            CustomTextView btnOK = dialogNotify.findViewById(R.id.btn_ok);
            btnOK.setOnClickListener(view -> dialogNotify.dismiss());

        }
        dialogNotify.setOnDismissListener(dialogInterface -> isDialogShowing = false);
        if (!dialogNotify.isShowing() && !isDialogShowing) {
            dialogNotify.show();
            isDialogShowing = true;
        }
    }

    public static void showDialogConfirm(final Context mContext, String message, String actionRight, String actionLeft, IDialogListener iDialogListener) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.dialog_confirm);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        CustomTextView tvMessage = dialog.findViewById(R.id.tv_message);
        tvMessage.setText(message);
        CustomTextView btnActionLeft = dialog.findViewById(R.id.btn_yes);
        btnActionLeft.setText(actionLeft);

        CustomTextView btnActionRight = dialog.findViewById(R.id.btn_no);
        btnActionRight.setText(actionRight);

        btnActionLeft.setOnClickListener(view -> {
            dialog.dismiss();
            iDialogListener.negativeClick();
        });
        btnActionRight.setOnClickListener(view -> {
            dialog.dismiss();
            iDialogListener.positiveClick();

        });
        dialog.show();

    }

    public static void showDialogConfirmOneAction(final Context mContext, String message, String actionConfirm, IDialogListener iDialogListener) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.dialog_confirm);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView tvMessage = dialog.findViewById(R.id.tv_message);
        tvMessage.setText(message);
        TextView btnConfirm = dialog.findViewById(R.id.btn_yes);
        btnConfirm.setText(actionConfirm);
        TextView btnCancel = dialog.findViewById(R.id.btn_no);
        btnCancel.setVisibility(View.GONE);
        btnConfirm.setOnClickListener(view -> {
            dialog.dismiss();
            iDialogListener.positiveClick();
        });
        btnCancel.setOnClickListener(view -> {
            dialog.dismiss();
            iDialogListener.negativeClick();
        });
        dialog.show();

    }

    public interface IDialogListener {
        default void positiveClick() {

        }

        default void negativeClick() {

        }
    }
}
