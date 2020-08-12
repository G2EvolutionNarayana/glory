package com.glory.apk.Model.WithDrawAmount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithDrawExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private WithDrawResponse withDrawResponse;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WithDrawResponse getWithDrawResponse() {
        return withDrawResponse;
    }

    public void setWithDrawResponse(WithDrawResponse withDrawResponse) {
        this.withDrawResponse = withDrawResponse;
    }
}
