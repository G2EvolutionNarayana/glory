package com.glory.apk.Model.UpdatePassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePasswordExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private UpdatePasswordResponse updatePasswordResponse;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UpdatePasswordResponse getUpdatePasswordResponse() {
        return updatePasswordResponse;
    }

    public void setUpdatePasswordResponse(UpdatePasswordResponse updatePasswordResponse) {
        this.updatePasswordResponse = updatePasswordResponse;
    }

}
