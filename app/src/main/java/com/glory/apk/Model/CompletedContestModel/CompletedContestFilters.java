package com.glory.apk.Model.CompletedContestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompletedContestFilters {
    @SerializedName("match_id")
    @Expose
    private String matchId;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

}
