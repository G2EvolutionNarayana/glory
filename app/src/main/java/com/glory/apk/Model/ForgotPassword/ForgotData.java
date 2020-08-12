package com.glory.apk.Model.ForgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgotData {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("forgot_exp")
    @Expose
    private Integer forgotExp;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getForgotExp() {
        return forgotExp;
    }

    public void setForgotExp(Integer forgotExp) {
        this.forgotExp = forgotExp;
    }

}