package com.glory.apk.Model.Contest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContestFilters {

    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("package_id")
    @Expose
    private String packageId;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
}
