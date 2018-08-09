package com.basemvp.android.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: gop_labo_android
 */

public class BaseResponse {

    @SerializedName("statusCode")
    public int statusCode;
    @SerializedName("statusMessage")
    public String statusMessage;
    @SerializedName("dialogMessage")
    public String dialogMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getDialogMessage() {
        return dialogMessage;
    }

    public void setDialogMessage(String dialogMessage) {
        this.dialogMessage = dialogMessage;
    }
}
