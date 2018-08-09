package com.basemvp.android.common;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.basemvp.android.R;
import com.basemvp.android.network.BaseResponse;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public class DialogUtils {
    private static AlertDialog alertDialog;
    private static boolean isDialogShowing = false;

    /**
     * Dialog show error when request api
     * @param errorResponse
     */

    public static void showDialogError(final Context context, final BaseResponse errorResponse) {
        if (alertDialog == null || ((ContextThemeWrapper) alertDialog.getContext()).getBaseContext() != context) {
            AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
            alertDialog = aBuilder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);

        }
        String message = context.getString(R.string.msg_request_default_error);
        if (errorResponse.getDialogMessage() == null || errorResponse.getDialogMessage().equals("")) {
            alertDialog.setMessage(message);
        } else {
            alertDialog.setMessage(errorResponse.getDialogMessage());
        }

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.action_dialog_ok), (dialog, which) -> {
            alertDialog.dismiss();
        });
        alertDialog.setOnDismissListener(dialogInterface -> isDialogShowing = false);
        if (!alertDialog.isShowing() && !isDialogShowing) {
            alertDialog.show();
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
        if (alertDialog == null || ((ContextThemeWrapper) alertDialog.getContext()).getBaseContext() != context) {
            AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
            alertDialog = aBuilder.create();
            alertDialog.setCancelable(false);
            alertDialog.setCanceledOnTouchOutside(false);
        }
        String message = context.getString(R.string.msg_request_default_error);
        if (t instanceof SocketTimeoutException) {
            message = context.getString(R.string.msg_request_timeout);
        } else if (t instanceof IOException) {
            message = context.getString(R.string.msg_request_no_internet);
        }
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.action_dialog_ok), (dialog, which) -> {
            alertDialog.dismiss();
        });
        alertDialog.setOnDismissListener(dialogInterface -> isDialogShowing = false);
        if (!alertDialog.isShowing() && !isDialogShowing) {
            alertDialog.show();
            isDialogShowing = true;
        }
    }

    /**
     * showDialogMessage
     */
    public static void showDialogMessage(final Context context, final String message) {
        if (alertDialog == null || ((ContextThemeWrapper) alertDialog.getContext()).getBaseContext() != context) {
            AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
            alertDialog = aBuilder.create();

        }
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.action_dialog_ok), (dialog, which) -> {
            alertDialog.dismiss();
        });
        alertDialog.setOnDismissListener(dialogInterface -> isDialogShowing = false);
        if (!alertDialog.isShowing() && !isDialogShowing) {
            alertDialog.show();
            isDialogShowing = true;
        }
    }

    /**
     * showDialogMessage
     */
    public static void showDialogMessage(final Context context, String title, final String message) {
        if (alertDialog == null || ((ContextThemeWrapper) alertDialog.getContext()).getBaseContext() != context) {
            AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
            alertDialog = aBuilder.create();

        }
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.action_dialog_ok), (dialog, which) -> {
            alertDialog.dismiss();
        });
        alertDialog.setOnDismissListener(dialogInterface -> isDialogShowing = false);
        if (!alertDialog.isShowing() && !isDialogShowing) {
            alertDialog.show();
            isDialogShowing = true;
        }
    }

    public static void showDialogConfirm(final Context mContext, String message, String actionRight, String actionLeft, IDialogListener iDialogListener) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.custom_dialog_confirm);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView tvMessage = dialog.findViewById(R.id.tv_message);
        tvMessage.setText(message);
        TextView btnActionLeft = dialog.findViewById(R.id.btn_yes);
        btnActionLeft.setText(actionLeft);

        TextView btnActionRight = dialog.findViewById(R.id.btn_no);
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
        dialog.setContentView(R.layout.custom_dialog_confirm);
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
