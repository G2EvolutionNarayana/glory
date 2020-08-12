package com.glory.apk.Model.LiveContestListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveContestExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private LiveContestResponse liveContestResponse;
    @SerializedName("data")
    @Expose
    private List<LiveContestDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private LiveContestFilters liveContestFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LiveContestResponse getLiveContestResponse() {
        return liveContestResponse;
    }

    public void setLiveContestResponse(LiveContestResponse liveContestResponse) {
        this.liveContestResponse = liveContestResponse;
    }

    public List<LiveContestDatum> getData() {
        return data;
    }

    public void setData(List<LiveContestDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public LiveContestFilters getLiveContestFilters() {
        return liveContestFilters;
    }

    public void setLiveContestFilters(LiveContestFilters liveContestFilters) {
        this.liveContestFilters = liveContestFilters;
    }
}
