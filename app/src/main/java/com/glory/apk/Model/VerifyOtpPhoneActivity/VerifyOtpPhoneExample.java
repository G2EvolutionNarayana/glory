package com.glory.apk.Model.VerifyOtpPhoneActivity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpPhoneExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private VerifyOtpPhoneResponse verifyOtpPhoneResponse;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VerifyOtpPhoneResponse getVerifyOtpPhoneResponse() {
        return verifyOtpPhoneResponse;
    }

    public void setVerifyOtpPhoneResponse(VerifyOtpPhoneResponse verifyOtpPhoneResponse) {
        this.verifyOtpPhoneResponse = verifyOtpPhoneResponse;
    }
}
