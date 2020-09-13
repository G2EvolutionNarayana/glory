package com.glory.apk.Model.MyMatchesCompleted;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyMatchesCompletedDatum {

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
    private Integer matchTypeId;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("currentMatchState")
    @Expose
    private String currentMatchState;
    @SerializedName("isMultiDay")
    @Expose
    private Integer isMultiDay;
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
    @SerializedName("series")
    @Expose
    private MyMatchesCompletedSeries myMatchesCompletedSeries;
    @SerializedName("hometeam")
    @Expose
    private MyMatchesCompletedHometeam myMatchesCompletedHometeam;
    @SerializedName("awayteam")
    @Expose
    private MyMatchesCompletedAwayteam myMatchesCompletedAwayteam;
    @SerializedName("venue")
    @Expose
    private MyMatchesCompletedVenue myMatchesCompletedVenue;
    @SerializedName("datetime")
    @Expose
    private MyMatchesCompletedDatetime myMatchesCompletedDatetime;

    @SerializedName("time_left")
    @Expose
    private String time_left;

    @SerializedName("season_name")
    @Expose
    private String season_name;

    public void setSeason_name(String season_name) {
        this.season_name = season_name;
    }

    public String getSeason_name() {
        return season_name;
    }

    public String getTime_left() {
        return time_left;
    }

    public void setTime_left(String time_left) {
        this.time_left = time_left;
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

    public Integer getMatchTypeId() {
        return matchTypeId;
    }

    public void setMatchTypeId(Integer matchTypeId) {
        this.matchTypeId = matchTypeId;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
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

    public Integer getIsMultiDay() {
        return isMultiDay;
    }

    public void setIsMultiDay(Integer isMultiDay) {
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

    public MyMatchesCompletedSeries getMyMatchesCompletedSeries() {
        return myMatchesCompletedSeries;
    }

    public void setMyMatchesCompletedSeries(MyMatchesCompletedSeries myMatchesCompletedSeries) {
        this.myMatchesCompletedSeries = myMatchesCompletedSeries;
    }

    public MyMatchesCompletedHometeam getMyMatchesCompletedHometeam() {
        return myMatchesCompletedHometeam;
    }

    public void setMyMatchesCompletedHometeam(MyMatchesCompletedHometeam myMatchesCompletedHometeam) {
        this.myMatchesCompletedHometeam = myMatchesCompletedHometeam;
    }

    public MyMatchesCompletedAwayteam getMyMatchesCompletedAwayteam() {
        return myMatchesCompletedAwayteam;
    }

    public void setMyMatchesCompletedAwayteam(MyMatchesCompletedAwayteam myMatchesCompletedAwayteam) {
        this.myMatchesCompletedAwayteam = myMatchesCompletedAwayteam;
    }

    public MyMatchesCompletedVenue getMyMatchesCompletedVenue() {
        return myMatchesCompletedVenue;
    }

    public void setMyMatchesCompletedVenue(MyMatchesCompletedVenue myMatchesCompletedVenue) {
        this.myMatchesCompletedVenue = myMatchesCompletedVenue;
    }

    public MyMatchesCompletedDatetime getMyMatchesCompletedDatetime() {
        return myMatchesCompletedDatetime;
    }

    public void setMyMatchesCompletedDatetime(MyMatchesCompletedDatetime myMatchesCompletedDatetime) {
        this.myMatchesCompletedDatetime = myMatchesCompletedDatetime;
    }

}
