package com.glory.apk.Model.LivePointsDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LivePointsDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("contest_user_id")
    @Expose
    private Integer contestUserId;
    @SerializedName("player_id")
    @Expose
    private Integer playerId;
    @SerializedName("player_type_id")
    @Expose
    private Integer playerTypeId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("player_score")
    @Expose
    private LivePointsPlayerScore livePointsPlayerScore;
    @SerializedName("player")
    @Expose
    private LivePointsPlayer livePointsPlayer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContestUserId() {
        return contestUserId;
    }

    public void setContestUserId(Integer contestUserId) {
        this.contestUserId = contestUserId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getPlayerTypeId() {
        return playerTypeId;
    }

    public void setPlayerTypeId(Integer playerTypeId) {
        this.playerTypeId = playerTypeId;
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

    public LivePointsPlayerScore getLivePointsPlayerScore() {
        return livePointsPlayerScore;
    }

    public void setLivePointsPlayerScore(LivePointsPlayerScore livePointsPlayerScore) {
        this.livePointsPlayerScore = livePointsPlayerScore;
    }

    public LivePointsPlayer getLivePointsPlayer() {
        return livePointsPlayer;
    }

    public void setLivePointsPlayer(LivePointsPlayer livePointsPlayer) {
        this.livePointsPlayer = livePointsPlayer;
    }
}
