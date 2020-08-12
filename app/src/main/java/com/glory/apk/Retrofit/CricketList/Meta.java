package com.glory.apk.Retrofit.CricketList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("upcomingMatchCount")
    @Expose
    private Integer upcomingMatchCount;
    @SerializedName("inProgressMatchCount")
    @Expose
    private Integer inProgressMatchCount;
    @SerializedName("completedMatchCount")
    @Expose
    private Integer completedMatchCount;

    public Integer getUpcomingMatchCount() {
        return upcomingMatchCount;
    }

    public void setUpcomingMatchCount(Integer upcomingMatchCount) {
        this.upcomingMatchCount = upcomingMatchCount;
    }

    public Integer getInProgressMatchCount() {
        return inProgressMatchCount;
    }

    public void setInProgressMatchCount(Integer inProgressMatchCount) {
        this.inProgressMatchCount = inProgressMatchCount;
    }

    public Integer getCompletedMatchCount() {
        return completedMatchCount;
    }

    public void setCompletedMatchCount(Integer completedMatchCount) {
        this.completedMatchCount = completedMatchCount;
    }

}
