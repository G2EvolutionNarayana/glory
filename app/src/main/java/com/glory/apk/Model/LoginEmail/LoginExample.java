package com.glory.apk.Model.LoginEmail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginExample {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private LoginResponse loginResponse;
    @SerializedName("data")
    @Expose
    private LoginEmailData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public LoginEmailData getData() {
        return data;
    }

    public void setData(LoginEmailData data) {
        this.data = data;
    }

}
