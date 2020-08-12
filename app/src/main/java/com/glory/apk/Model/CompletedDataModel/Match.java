package com.glory.apk.Model.CompletedDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("match_id")
    @Expose
    private Object matchId;
    @SerializedName("match_key")
    @Expose
    private String matchKey;
    @SerializedName("season_key")
    @Expose
    private String seasonKey;
    @SerializedName("season_name")
    @Expose
    private String seasonName;
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
    private Object winningTeamId;
    @SerializedName("isWomensMatch")
    @Expose
    private Object isWomensMatch;
    @SerializedName("cmsMatchType")
    @Expose
    private Object cmsMatchType;
    @SerializedName("cmsMatchAssociatedType")
    @Expose
    private String cmsMatchAssociatedType;
    @SerializedName("cmsMatchVenueStartDateTime")
    @Expose
    private String cmsMatchVenueStartDateTime;
    @SerializedName("cmsMatchVenueEndDateTime")
    @Expose
    private String cmsMatchVenueEndDateTime;
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

    public Object getMatchId() {
        return matchId;
    }

    public void setMatchId(Object matchId) {
        this.matchId = matchId;
    }

    public String getMatchKey() {
        return matchKey;
    }

    public void setMatchKey(String matchKey) {
        this.matchKey = matchKey;
    }

    public String getSeasonKey() {
        return seasonKey;
    }

    public void setSeasonKey(String seasonKey) {
        this.seasonKey = seasonKey;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
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

    public Object getWinningTeamId() {
        return winningTeamId;
    }

    public void setWinningTeamId(Object winningTeamId) {
        this.winningTeamId = winningTeamId;
    }

    public Object getIsWomensMatch() {
        return isWomensMatch;
    }

    public void setIsWomensMatch(Object isWomensMatch) {
        this.isWomensMatch = isWomensMatch;
    }

    public Object getCmsMatchType() {
        return cmsMatchType;
    }

    public void setCmsMatchType(Object cmsMatchType) {
        this.cmsMatchType = cmsMatchType;
    }

    public String getCmsMatchAssociatedType() {
        return cmsMatchAssociatedType;
    }

    public void setCmsMatchAssociatedType(String cmsMatchAssociatedType) {
        this.cmsMatchAssociatedType = cmsMatchAssociatedType;
    }

    public String getCmsMatchVenueStartDateTime() {
        return cmsMatchVenueStartDateTime;
    }

    public void setCmsMatchVenueStartDateTime(String cmsMatchVenueStartDateTime) {
        this.cmsMatchVenueStartDateTime = cmsMatchVenueStartDateTime;
    }

    public String getCmsMatchVenueEndDateTime() {
        return cmsMatchVenueEndDateTime;
    }

    public void setCmsMatchVenueEndDateTime(String cmsMatchVenueEndDateTime) {
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

}
