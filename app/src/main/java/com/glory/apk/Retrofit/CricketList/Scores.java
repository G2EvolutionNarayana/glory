package com.glory.apk.Retrofit.CricketList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scores {

    @SerializedName("homeScore")
    @Expose
    private String homeScore;
    @SerializedName("homeOvers")
    @Expose
    private String homeOvers;
    @SerializedName("awayScore")
    @Expose
    private String awayScore;
    @SerializedName("awayOvers")
    @Expose
    private String awayOvers;

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getHomeOvers() {
        return homeOvers;
    }

    public void setHomeOvers(String homeOvers) {
        this.homeOvers = homeOvers;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public String getAwayOvers() {
        return awayOvers;
    }

    public void setAwayOvers(String awayOvers) {
        this.awayOvers = awayOvers;
    }

}