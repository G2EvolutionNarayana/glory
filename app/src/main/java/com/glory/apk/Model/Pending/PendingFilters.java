package com.glory.apk.Model.Pending;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PendingFilters {
    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
