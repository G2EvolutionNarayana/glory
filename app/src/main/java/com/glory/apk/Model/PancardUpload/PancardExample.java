package com.glory.apk.Model.PancardUpload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PancardExample {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private PancardResponse pancardResponse;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PancardResponse getResponse() {
        return pancardResponse;
    }

    public void setResponse(PancardResponse pancardResponse) {
        this.pancardResponse = pancardResponse;
    }
}
