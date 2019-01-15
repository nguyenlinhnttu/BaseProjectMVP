package com.base.android.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenvanlinh on 2/28/18.
 * Project: BaseProject
 */

public class BaseResponse {

    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("statusMessage")
    private String statusMessage;
    @SerializedName("dialogMessage")
    private String dialogMessage;

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
