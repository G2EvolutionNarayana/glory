package com.glory.apk.Model.MyMatches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("season_name")
    @Expose
    private String season_name;

    public String getSeason_name() {
        return season_name;
    }

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

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
    private Object matchSummaryText;
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
    private Integer isWomensMatch;
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
    @SerializedName("series")
    @Expose
    private Series series;
    @SerializedName("hometeam")
    @Expose
    private Hometeam hometeam;
    @SerializedName("awayteam")
    @Expose
    private Awayteam awayteam;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("datetime")
    @Expose
    private Datetime datetime;

    @SerializedName("record_entry_status")
    @Expose
    private String record_entry_status;

    public String getRecord_entry_status() {
        return record_entry_status;
    }

    public void setRecord_entry_status(String record_entry_status) {
        this.record_entry_status = record_entry_status;
    }

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

    public Object getMatchSummaryText() {
        return matchSummaryText;
    }

    public void setMatchSummaryText(Object matchSummaryText) {
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

    public Integer getIsWomensMatch() {
        return isWomensMatch;
    }

    public void setIsWomensMatch(Integer isWomensMatch) {
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

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Hometeam getHometeam() {
        return hometeam;
    }

    public void setHometeam(Hometeam hometeam) {
        this.hometeam = hometeam;
    }

    public Awayteam getAwayteam() {
        return awayteam;
    }

    public void setAwayteam(Awayteam awayteam) {
        this.awayteam = awayteam;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Datetime getDatetime() {
        return datetime;
    }

    public void setDatetime(Datetime datetime) {
        this.datetime = datetime;
    }

}

