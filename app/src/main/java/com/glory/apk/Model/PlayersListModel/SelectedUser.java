package com.glory.apk.Model.PlayersListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedUser {
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
    @SerializedName("player")
    @Expose
    private Play_listPlayer playlistPlayer;

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

    public Play_listPlayer getPlaylistPlayer() {
        return playlistPlayer;
    }

    public void setPlaylistPlayer(Play_listPlayer playlistPlayer) {
        this.playlistPlayer = playlistPlayer;
    }
}
