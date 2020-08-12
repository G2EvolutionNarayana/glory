package com.glory.apk.Model.VerifyEmailModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyEmailExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private VerifyEmailResponse response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VerifyEmailResponse getResponse() {
        return response;
    }

    public void setResponse(VerifyEmailResponse response) {
        this.response = response;
    }
}
