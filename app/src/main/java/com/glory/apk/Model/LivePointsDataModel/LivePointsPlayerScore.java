package com.glory.apk.Model.LivePointsDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LivePointsPlayerScore {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("player_id")
    @Expose
    private String playerId;
    @SerializedName("normal_player_points")
    @Expose
    private String normalPlayerPoints;
    @SerializedName("power_hitter_points")
    @Expose
    private String powerHitterPoints;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getNormalPlayerPoints() {
        return normalPlayerPoints;
    }

    public void setNormalPlayerPoints(String normalPlayerPoints) {
        this.normalPlayerPoints = normalPlayerPoints;
    }

    public String getPowerHitterPoints() {
        return powerHitterPoints;
    }

    public void setPowerHitterPoints(String powerHitterPoints) {
        this.powerHitterPoints = powerHitterPoints;
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
