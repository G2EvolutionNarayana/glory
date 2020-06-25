package com.android.glory.Model.MyMatchesCompleted;

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
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private MyMatchesCompletedFilters myMatchesCompletedFilters;

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

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public MyMatchesCompletedFilters getMyMatchesCompletedFilters() {
        return myMatchesCompletedFilters;
    }

    public void setMyMatchesCompletedFilters(MyMatchesCompletedFilters myMatchesCompletedFilters) {
        this.myMatchesCompletedFilters = myMatchesCompletedFilters;
    }

}
