package com.glory.apk.Model.Contest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContestExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private ContestResponse contestResponse;
    @SerializedName("data")
    @Expose
    private List<ContestDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private ContestFilters contestFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ContestResponse getContestResponse() {
        return contestResponse;
    }

    public void setContestResponse(ContestResponse contestResponse) {
        this.contestResponse = contestResponse;
    }

    public List<ContestDatum> getData() {
        return data;
    }

    public void setData(List<ContestDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public ContestFilters getContestFilters() {
        return contestFilters;
    }

    public void setContestFilters(ContestFilters contestFilters) {
        this.contestFilters = contestFilters;
    }
}
