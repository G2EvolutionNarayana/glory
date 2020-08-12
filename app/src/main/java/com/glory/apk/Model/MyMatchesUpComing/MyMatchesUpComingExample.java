package com.glory.apk.Model.MyMatchesUpComing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyMatchesUpComingExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private MyMatchesUpComingResponse myMatchesUpComingResponse;
    @SerializedName("data")
    @Expose
    private List<MyMatchesUpComingDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private MyMatchesUpComingFilters myMatchesUpComingFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MyMatchesUpComingResponse getMyMatchesUpComingResponse() {
        return myMatchesUpComingResponse;
    }

    public void setMyMatchesUpComingResponse(MyMatchesUpComingResponse myMatchesUpComingResponse) {
        this.myMatchesUpComingResponse = myMatchesUpComingResponse;
    }

    public List<MyMatchesUpComingDatum> getData() {
        return data;
    }

    public void setData(List<MyMatchesUpComingDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public MyMatchesUpComingFilters getMyMatchesUpComingFilters() {
        return myMatchesUpComingFilters;
    }

    public void setMyMatchesUpComingFilters(MyMatchesUpComingFilters myMatchesUpComingFilters) {
        this.myMatchesUpComingFilters = myMatchesUpComingFilters;
    }

}
