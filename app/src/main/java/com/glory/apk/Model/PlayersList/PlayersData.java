package com.glory.apk.Model.PlayersList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayersData {
    @SerializedName("homeTeam")
    @Expose
    private List<PlayersListHomeTeam> playersListHomeTeam = null;
    @SerializedName("awayTeam")
    @Expose
    private List<PlayersAwayTeam> playersAwayTeam = null;

    public List<PlayersListHomeTeam> getPlayersListHomeTeam() {
        return playersListHomeTeam;
    }

    public void setPlayersListHomeTeam(List<PlayersListHomeTeam> playersListHomeTeam) {
        this.playersListHomeTeam = playersListHomeTeam;
    }

    public List<PlayersAwayTeam> getPlayersAwayTeam() {
        return playersAwayTeam;
    }

    public void setPlayersAwayTeam(List<PlayersAwayTeam> playersAwayTeam) {
        this.playersAwayTeam = playersAwayTeam;
    }
}
