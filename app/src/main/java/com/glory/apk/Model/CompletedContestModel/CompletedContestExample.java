package com.glory.apk.Model.CompletedContestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompletedContestExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private CompletedContestResponse completedContestResponse;
    @SerializedName("data")
    @Expose
    private List<CompletedContestDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private CompletedContestFilters completedContestFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CompletedContestResponse getCompletedContestResponse() {
        return completedContestResponse;
    }

    public void setCompletedContestResponse(CompletedContestResponse completedContestResponse) {
        this.completedContestResponse = completedContestResponse;
    }

    public List<CompletedContestDatum> getData() {
        return data;
    }

    public void setData(List<CompletedContestDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public CompletedContestFilters getCompletedContestFilters() {
        return completedContestFilters;
    }

    public void setCompletedContestFilters(CompletedContestFilters completedContestFilters) {
        this.completedContestFilters = completedContestFilters;
    }
}
