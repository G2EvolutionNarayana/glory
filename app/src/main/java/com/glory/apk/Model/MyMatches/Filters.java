package com.glory.apk.Model.MyMatches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filters {
    @SerializedName("match_status")
    @Expose
    private String matchStatus;

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

}
