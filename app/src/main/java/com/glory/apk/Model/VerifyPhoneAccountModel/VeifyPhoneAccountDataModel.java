package com.glory.apk.Model.VerifyPhoneAccountModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VeifyPhoneAccountDataModel {
    @SerializedName("otp")
    @Expose
    private Integer otp;

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }
}
