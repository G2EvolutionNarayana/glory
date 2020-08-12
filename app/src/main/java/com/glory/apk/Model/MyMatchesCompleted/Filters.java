package com.glory.apk.Model.MyMatchesCompleted;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filters {
    @SerializedName("match_status")
    @Expose
    private String matchStatus;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("pagination")
    @Expose
    private String pagination;
    @SerializedName("limit")
    @Expose
    private String limit;

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

}
