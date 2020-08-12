package com.glory.apk.Model.EditPlayersListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditPlayerHomeTeam {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("player_id")
    @Expose
    private Integer playerId;
    @SerializedName("credits")
    @Expose
    private String credits;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("isselected")
    @Expose
    private Integer isselected;
    @SerializedName("player")
    @Expose
    private EditPlayerPlayer editPlayerPlayer;

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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
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

    public Integer getIsselected() {
        return isselected;
    }

    public void setIsselected(Integer isselected) {
        this.isselected = isselected;
    }

    public EditPlayerPlayer getEditPlayerPlayer() {
        return editPlayerPlayer;
    }

    public void setEditPlayerPlayer(EditPlayerPlayer editPlayerPlayer) {
        this.editPlayerPlayer = editPlayerPlayer;
    }
}
