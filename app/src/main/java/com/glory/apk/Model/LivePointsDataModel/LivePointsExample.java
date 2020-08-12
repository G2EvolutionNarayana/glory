package com.glory.apk.Model.LivePointsDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LivePointsExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private LivePointsResponse livePointsResponse;
    @SerializedName("data")
    @Expose
    private List<LivePointsDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private LivePointsFilters livePointsFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LivePointsResponse getLivePointsResponse() {
        return livePointsResponse;
    }

    public void setLivePointsResponse(LivePointsResponse livePointsResponse) {
        this.livePointsResponse = livePointsResponse;
    }

    public List<LivePointsDatum> getData() {
        return data;
    }

    public void setData(List<LivePointsDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public LivePointsFilters getLivePointsFilters() {
        return livePointsFilters;
    }

    public void setLivePointsFilters(LivePointsFilters livePointsFilters) {
        this.livePointsFilters = livePointsFilters;
    }
}
