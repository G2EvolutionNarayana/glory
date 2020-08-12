package com.glory.apk.Model.Logout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutExample {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private LogoutResponse response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LogoutResponse getResponse() {
        return response;
    }

    public void setResponse(LogoutResponse response) {
        this.response = response;
    }
}
