package com.glory.apk.Model.MyMatchesCompleted;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyMatchesCompletedExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private MyMatchesCompletedResponse myMatchesCompletedResponse;
    @SerializedName("data")
    @Expose
    private List<MyMatchesCompletedDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    @SerializedName("filters")
    @Expose
    private Filters filters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MyMatchesCompletedResponse getMyMatchesCompletedResponse() {
        return myMatchesCompletedResponse;
    }

    public void setMyMatchesCompletedResponse(MyMatchesCompletedResponse myMatchesCompletedResponse) {
        this.myMatchesCompletedResponse = myMatchesCompletedResponse;
    }

    public List<MyMatchesCompletedDatum> getData() {
        return data;
    }

    public void setData(List<MyMatchesCompletedDatum> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }


    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

}
