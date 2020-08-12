package com.glory.apk.Model.LiveMyMatchesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveMyMatchesExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private LiveMyMatchesResponse liveMyMatchesResponse;
    @SerializedName("data")
    @Expose
    private List<LiveMyMatchesDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private LiveMyMatchesFilters liveMyMatchesFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LiveMyMatchesResponse getLiveMyMatchesResponse() {
        return liveMyMatchesResponse;
    }

    public void setLiveMyMatchesResponse(LiveMyMatchesResponse liveMyMatchesResponse) {
        this.liveMyMatchesResponse = liveMyMatchesResponse;
    }

    public List<LiveMyMatchesDatum> getData() {
        return data;
    }

    public void setData(List<LiveMyMatchesDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public LiveMyMatchesFilters getLiveMyMatchesFilters() {
        return liveMyMatchesFilters;
    }

    public void setLiveMyMatchesFilters(LiveMyMatchesFilters liveMyMatchesFilters) {
        this.liveMyMatchesFilters = liveMyMatchesFilters;
    }

}
