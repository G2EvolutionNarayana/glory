package com.glory.apk.Model.UpdatePlayersModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePlayersExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private UpdatePlayersResponse updatePlayersResponse;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UpdatePlayersResponse getUpdatePlayersResponse() {
        return updatePlayersResponse;
    }

    public void setUpdatePlayersResponse(UpdatePlayersResponse updatePlayersResponse) {
        this.updatePlayersResponse = updatePlayersResponse;
    }
}
