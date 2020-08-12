package com.glory.apk.Model.EditPlayersListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditPlayerMatch {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("series_id")
    @Expose
    private Integer seriesId;
    @SerializedName("homeTeam_id")
    @Expose
    private Integer homeTeamId;
    @SerializedName("awayTeam_id")
    @Expose
    private Integer awayTeamId;
    @SerializedName("matchVenue_id")
    @Expose
    private Integer matchVenueId;
    @SerializedName("matchTypeId")
    @Expose
    private Object matchTypeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("currentMatchState")
    @Expose
    private String currentMatchState;
    @SerializedName("isMultiDay")
    @Expose
    private Object isMultiDay;
    @SerializedName("matchSummaryText")
    @Expose
    private String matchSummaryText;
    @SerializedName("isMatchDrawn")
    @Expose
    private Object isMatchDrawn;
    @SerializedName("isMatchAbandoned")
    @Expose
    private Object isMatchAbandoned;
    @SerializedName("winningTeamId")
    @Expose
    private Integer winningTeamId;
    @SerializedName("isWomensMatch")
    @Expose
    private Object isWomensMatch;
    @SerializedName("cmsMatchType")
    @Expose
    private String cmsMatchType;
    @SerializedName("cmsMatchAssociatedType")
    @Expose
    private String cmsMatchAssociatedType;
    @SerializedName("cmsMatchVenueStartDateTime")
    @Expose
    private Object cmsMatchVenueStartDateTime;
    @SerializedName("cmsMatchVenueEndDateTime")
    @Expose
    private Object cmsMatchVenueEndDateTime;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("homeTeam")
    @Expose
    private List<EditPlayerHomeTeam> editPlayerHomeTeam = null;
    @SerializedName("awayTeam")
    @Expose
    private List<EditPlayerAwayTeam> editPlayerAwayTeam = null;

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

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Integer getMatchVenueId() {
        return matchVenueId;
    }

    public void setMatchVenueId(Integer matchVenueId) {
        this.matchVenueId = matchVenueId;
    }

    public Object getMatchTypeId() {
        return matchTypeId;
    }

    public void setMatchTypeId(Object matchTypeId) {
        this.matchTypeId = matchTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentMatchState() {
        return currentMatchState;
    }

    public void setCurrentMatchState(String currentMatchState) {
        this.currentMatchState = currentMatchState;
    }

    public Object getIsMultiDay() {
        return isMultiDay;
    }

    public void setIsMultiDay(Object isMultiDay) {
        this.isMultiDay = isMultiDay;
    }

    public String getMatchSummaryText() {
        return matchSummaryText;
    }

    public void setMatchSummaryText(String matchSummaryText) {
        this.matchSummaryText = matchSummaryText;
    }

    public Object getIsMatchDrawn() {
        return isMatchDrawn;
    }

    public void setIsMatchDrawn(Object isMatchDrawn) {
        this.isMatchDrawn = isMatchDrawn;
    }

    public Object getIsMatchAbandoned() {
        return isMatchAbandoned;
    }

    public void setIsMatchAbandoned(Object isMatchAbandoned) {
        this.isMatchAbandoned = isMatchAbandoned;
    }

    public Integer getWinningTeamId() {
        return winningTeamId;
    }

    public void setWinningTeamId(Integer winningTeamId) {
        this.winningTeamId = winningTeamId;
    }

    public Object getIsWomensMatch() {
        return isWomensMatch;
    }

    public void setIsWomensMatch(Object isWomensMatch) {
        this.isWomensMatch = isWomensMatch;
    }

    public String getCmsMatchType() {
        return cmsMatchType;
    }

    public void setCmsMatchType(String cmsMatchType) {
        this.cmsMatchType = cmsMatchType;
    }

    public String getCmsMatchAssociatedType() {
        return cmsMatchAssociatedType;
    }

    public void setCmsMatchAssociatedType(String cmsMatchAssociatedType) {
        this.cmsMatchAssociatedType = cmsMatchAssociatedType;
    }

    public Object getCmsMatchVenueStartDateTime() {
        return cmsMatchVenueStartDateTime;
    }

    public void setCmsMatchVenueStartDateTime(Object cmsMatchVenueStartDateTime) {
        this.cmsMatchVenueStartDateTime = cmsMatchVenueStartDateTime;
    }

    public Object getCmsMatchVenueEndDateTime() {
        return cmsMatchVenueEndDateTime;
    }

    public void setCmsMatchVenueEndDateTime(Object cmsMatchVenueEndDateTime) {
        this.cmsMatchVenueEndDateTime = cmsMatchVenueEndDateTime;
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

    public List<EditPlayerHomeTeam> getEditPlayerHomeTeam() {
        return editPlayerHomeTeam;
    }

    public void setEditPlayerHomeTeam(List<EditPlayerHomeTeam> editPlayerHomeTeam) {
        this.editPlayerHomeTeam = editPlayerHomeTeam;
    }

    public List<EditPlayerAwayTeam> getEditPlayerAwayTeam() {
        return editPlayerAwayTeam;
    }

    public void setEditPlayerAwayTeam(List<EditPlayerAwayTeam> editPlayerAwayTeam) {
        this.editPlayerAwayTeam = editPlayerAwayTeam;
    }
}
