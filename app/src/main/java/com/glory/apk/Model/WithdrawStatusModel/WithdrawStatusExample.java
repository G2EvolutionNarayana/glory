package com.glory.apk.Model.WithdrawStatusModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WithdrawStatusExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private WithdrawStatusResponse withdrawStatusResponse;
    @SerializedName("data")
    @Expose
    private WithdrawStatusData data;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private WithdrawStatusFilters withdrawStatusFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WithdrawStatusResponse getWithdrawStatusResponse() {
        return withdrawStatusResponse;
    }

    public void setWithdrawStatusResponse(WithdrawStatusResponse withdrawStatusResponse) {
        this.withdrawStatusResponse = withdrawStatusResponse;
    }

    public WithdrawStatusData getData() {
        return data;
    }

    public void setData(WithdrawStatusData data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public WithdrawStatusFilters getWithdrawStatusFilters() {
        return withdrawStatusFilters;
    }

    public void setWithdrawStatusFilters(WithdrawStatusFilters withdrawStatusFilters) {
        this.withdrawStatusFilters = withdrawStatusFilters;
    }
}
