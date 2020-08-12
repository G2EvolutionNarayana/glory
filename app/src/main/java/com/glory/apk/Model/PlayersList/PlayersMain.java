package com.glory.apk.Model.PlayersList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayersMain {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("data")
    @Expose
    private PlayersData playersData;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private PlayerFilter playerFilter;

    public Boolean isSelected;

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public PlayersData getPlayersData() {
        return playersData;
    }

    public void setPlayersData(PlayersData playersData) {
        this.playersData = playersData;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public PlayerFilter getPlayerFilter() {
        return playerFilter;
    }

    public void setPlayerFilter(PlayerFilter playerFilter) {
        this.playerFilter = playerFilter;
    }

}
