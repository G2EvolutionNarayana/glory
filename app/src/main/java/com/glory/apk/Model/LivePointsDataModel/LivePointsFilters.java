package com.glory.apk.Model.LivePointsDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LivePointsFilters {
    @SerializedName("contest_user_id")
    @Expose
    private String contestUserId;
    @SerializedName("match_id")
    @Expose
    private String matchId;

    public String getContestUserId() {
        return contestUserId;
    }

    public void setContestUserId(String contestUserId) {
        this.contestUserId = contestUserId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
