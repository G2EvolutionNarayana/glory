package com.glory.apk.Model.PackagesList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageList {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("data")
    @Expose
    private List<PackageData> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private List<Object> filters = null;

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

    public List<PackageData> getData() {
        return data;
    }

    public void setData(List<PackageData> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public List<Object> getFilters() {
        return filters;
    }

    public void setFilters(List<Object> filters) {
        this.filters = filters;
    }

}
