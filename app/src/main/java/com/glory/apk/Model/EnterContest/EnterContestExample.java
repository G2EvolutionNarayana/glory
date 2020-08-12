package com.glory.apk.Model.EnterContest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnterContestExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private EnterContestResponse enterContestResponse;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EnterContestResponse getEnterContestResponse() {
        return enterContestResponse;
    }

    public void setEnterContestResponse(EnterContestResponse enterContestResponse) {
        this.enterContestResponse = enterContestResponse;
    }
}
