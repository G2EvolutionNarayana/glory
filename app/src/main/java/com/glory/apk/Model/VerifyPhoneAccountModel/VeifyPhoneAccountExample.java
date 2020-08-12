package com.glory.apk.Model.VerifyPhoneAccountModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VeifyPhoneAccountExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private VeifyPhoneAccountResponse veifyPhoneAccountResponse;
    @SerializedName("data")
    @Expose
    private VeifyPhoneAccountDataModel data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VeifyPhoneAccountResponse getResponse() {
        return veifyPhoneAccountResponse;
    }

    public void setResponse(VeifyPhoneAccountResponse veifyPhoneAccountResponse) {
        this.veifyPhoneAccountResponse = veifyPhoneAccountResponse;
    }

    public VeifyPhoneAccountDataModel getData() {
        return data;
    }

    public void setData(VeifyPhoneAccountDataModel data) {
        this.data = data;
    }
}
