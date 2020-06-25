package com.android.glory.Model.LiveMyMatchesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveMyMatchesFilters {
    @SerializedName("match_status")
    @Expose
    private String matchStatus;
    @SerializedName("user_id")
    @Expose
    private String userId;

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
}
