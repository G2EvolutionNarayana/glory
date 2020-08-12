package com.glory.apk.Model.LiveScore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("homeScore")
    @Expose
    private String homeScore;
    @SerializedName("homeOvers")
    @Expose
    private String homeOvers;
    @SerializedName("homeWickets")
    @Expose
    private String homeWickets;
    @SerializedName("awayScore")
    @Expose
    private String awayScore;
    @SerializedName("awayOvers")
    @Expose
    private String awayOvers;
    @SerializedName("awayWickets")
    @Expose
    private String awayWickets;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

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

    public String getHomeWickets() {
        return homeWickets;
    }

    public void setHomeWickets(String homeWickets) {
        this.homeWickets = homeWickets;
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

    public String getAwayWickets() {
        return awayWickets;
    }

    public void setAwayWickets(String awayWickets) {
        this.awayWickets = awayWickets;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
