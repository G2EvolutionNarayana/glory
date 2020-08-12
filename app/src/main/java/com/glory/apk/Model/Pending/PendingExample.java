package com.glory.apk.Model.Pending;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("data")
    @Expose
    private List<PendingDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private PendingFilters pendingFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<PendingDatum> getData() {
        return data;
    }

    public void setData(List<PendingDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public PendingFilters getPendingFilters() {
        return pendingFilters;
    }

    public void setPendingFilters(PendingFilters pendingFilters) {
        this.pendingFilters = pendingFilters;
    }
}
