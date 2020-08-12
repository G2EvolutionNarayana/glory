package com.glory.apk.Model.ForgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotMain {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private ForgotResposnse forgotResposnse;
    @SerializedName("data")
    @Expose
    private ForgotData forgotData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ForgotResposnse getForgotResposnse() {
        return forgotResposnse;
    }

    public void setForgotResposnse(ForgotResposnse forgotResposnse) {
        this.forgotResposnse = forgotResposnse;
    }

    public ForgotData getForgotData() {
        return forgotData;
    }

    public void setForgotData(ForgotData forgotData) {
        this.forgotData = forgotData;
    }

}

