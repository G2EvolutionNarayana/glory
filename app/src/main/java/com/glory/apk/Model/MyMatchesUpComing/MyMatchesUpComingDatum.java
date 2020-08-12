package com.glory.apk.Model.MyMatchesUpComing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyMatchesUpComingDatum {

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
    private String name;
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
    private MyMatchesUpComingSeries myMatchesUpComingSeries;
    @SerializedName("hometeam")
    @Expose
    private MyMatchesUpComingHometeam myMatchesUpComingHometeam;
    @SerializedName("awayteam")
    @Expose
    private MyMatchesUpComingAwayteam myMatchesUpComingAwayteam;
    @SerializedName("venue")
    @Expose
    private MyMatchesUpComingVenue myMatchesUpComingVenue;
    @SerializedName("datetime")
    @Expose
    private MyMatchesUpComingDatetime myMatchesUpComingDatetime;

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

    public Integer getIsMultiDay() {
        return isMultiDay;
    }

    public void setIsMultiDay(Integer isMultiDay) {
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

    public MyMatchesUpComingSeries getMyMatchesUpComingSeries() {
        return myMatchesUpComingSeries;
    }

    public void setMyMatchesUpComingSeries(MyMatchesUpComingSeries myMatchesUpComingSeries) {
        this.myMatchesUpComingSeries = myMatchesUpComingSeries;
    }

    public MyMatchesUpComingHometeam getMyMatchesUpComingHometeam() {
        return myMatchesUpComingHometeam;
    }

    public void setMyMatchesUpComingHometeam(MyMatchesUpComingHometeam myMatchesUpComingHometeam) {
        this.myMatchesUpComingHometeam = myMatchesUpComingHometeam;
    }

    public MyMatchesUpComingAwayteam getMyMatchesUpComingAwayteam() {
        return myMatchesUpComingAwayteam;
    }

    public void setMyMatchesUpComingAwayteam(MyMatchesUpComingAwayteam myMatchesUpComingAwayteam) {
        this.myMatchesUpComingAwayteam = myMatchesUpComingAwayteam;
    }

    public MyMatchesUpComingVenue getMyMatchesUpComingVenue() {
        return myMatchesUpComingVenue;
    }

    public void setMyMatchesUpComingVenue(MyMatchesUpComingVenue myMatchesUpComingVenue) {
        this.myMatchesUpComingVenue = myMatchesUpComingVenue;
    }

    public MyMatchesUpComingDatetime getMyMatchesUpComingDatetime() {
        return myMatchesUpComingDatetime;
    }

    public void setMyMatchesUpComingDatetime(MyMatchesUpComingDatetime myMatchesUpComingDatetime) {
        this.myMatchesUpComingDatetime = myMatchesUpComingDatetime;
    }

}
