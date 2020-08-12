package com.glory.apk.Model.VerifyBankDetailsActivity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyBankDetailsExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private VerifyBankDetailsResponse verifyBankDetailsResponse;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VerifyBankDetailsResponse getVerifyBankDetailsResponse() {
        return verifyBankDetailsResponse;
    }

    public void setVerifyBankDetailsResponse(VerifyBankDetailsResponse verifyBankDetailsResponse) {
        this.verifyBankDetailsResponse = verifyBankDetailsResponse;
    }
}
