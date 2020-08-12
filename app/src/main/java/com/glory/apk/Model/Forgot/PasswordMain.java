package com.glory.apk.Model.Forgot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PasswordMain {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private PasswordResponse passwordResponse;
    @SerializedName("data")
    @Expose
    private PasswordData passwordData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PasswordResponse getPasswordResponse() {
        return passwordResponse;
    }

    public void setPasswordResponse(PasswordResponse passwordResponse) {
        this.passwordResponse = passwordResponse;
    }

    public PasswordData getPasswordData() {
        return passwordData;
    }

    public void setPasswordData(PasswordData passwordData) {
        this.passwordData = passwordData;
    }
}
