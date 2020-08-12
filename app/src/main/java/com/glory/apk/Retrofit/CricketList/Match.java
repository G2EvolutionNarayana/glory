package com.glory.apk.Retrofit.CricketList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Match {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("matchTypeId")
    @Expose
    private Integer matchTypeId;
    @SerializedName("statisticsProvider")
    @Expose
    private String statisticsProvider;
    @SerializedName("series")
    @Expose
    private Series series;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("homeTeam")
    @Expose
    private HomeTeam homeTeam;
    @SerializedName("awayTeam")
    @Expose
    private AwayTeam awayTeam;
    @SerializedName("currentMatchState")
    @Expose
    private String currentMatchState;
    @SerializedName("isMultiDay")
    @Expose
    private Boolean isMultiDay;
    @SerializedName("matchSummaryText")
    @Expose
    private String matchSummaryText;
    @SerializedName("scores")
    @Expose
    private Scores scores;
    @SerializedName("liveStreams")
    @Expose
    private List<Object> liveStreams = null;
    @SerializedName("isLive")
    @Expose
    private Boolean isLive;
    @SerializedName("currentInningId")
    @Expose
    private Integer currentInningId;
    @SerializedName("isMatchDrawn")
    @Expose
    private Boolean isMatchDrawn;
    @SerializedName("isMatchAbandoned")
    @Expose
    private Boolean isMatchAbandoned;
    @SerializedName("winningTeamId")
    @Expose
    private Integer winningTeamId;
    @SerializedName("startDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("endDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("isWomensMatch")
    @Expose
    private Boolean isWomensMatch;
    @SerializedName("cmsMatchType")
    @Expose
    private String cmsMatchType;
    @SerializedName("cmsMatchAssociatedType")
    @Expose
    private String cmsMatchAssociatedType;
    @SerializedName("cmsMatchVenueStartDateTime")
    @Expose
    private String cmsMatchVenueStartDateTime;
    @SerializedName("cmsMatchVenueEndDateTime")
    @Expose
    private String cmsMatchVenueEndDateTime;
    @SerializedName("gamedayStatus")
    @Expose
    private String gamedayStatus;
    @SerializedName("isGamedayEnabled")
    @Expose
    private Boolean isGamedayEnabled;
    @SerializedName("removeMatch")
    @Expose
    private Boolean removeMatch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatchTypeId() {
        return matchTypeId;
    }

    public void setMatchTypeId(Integer matchTypeId) {
        this.matchTypeId = matchTypeId;
    }

    public String getStatisticsProvider() {
        return statisticsProvider;
    }

    public void setStatisticsProvider(String statisticsProvider) {
        this.statisticsProvider = statisticsProvider;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
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

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getCurrentMatchState() {
        return currentMatchState;
    }

    public void setCurrentMatchState(String currentMatchState) {
        this.currentMatchState = currentMatchState;
    }

    public Boolean getIsMultiDay() {
        return isMultiDay;
    }

    public void setIsMultiDay(Boolean isMultiDay) {
        this.isMultiDay = isMultiDay;
    }

    public String getMatchSummaryText() {
        return matchSummaryText;
    }

    public void setMatchSummaryText(String matchSummaryText) {
        this.matchSummaryText = matchSummaryText;
    }

    public Scores getScores() {
        return scores;
    }

    public void setScores(Scores scores) {
        this.scores = scores;
    }

    public List<Object> getLiveStreams() {
        return liveStreams;
    }

    public void setLiveStreams(List<Object> liveStreams) {
        this.liveStreams = liveStreams;
    }

    public Boolean getIsLive() {
        return isLive;
    }

    public void setIsLive(Boolean isLive) {
        this.isLive = isLive;
    }

    public Integer getCurrentInningId() {
        return currentInningId;
    }

    public void setCurrentInningId(Integer currentInningId) {
        this.currentInningId = currentInningId;
    }

    public Boolean getIsMatchDrawn() {
        return isMatchDrawn;
    }

    public void setIsMatchDrawn(Boolean isMatchDrawn) {
        this.isMatchDrawn = isMatchDrawn;
    }

    public Boolean getIsMatchAbandoned() {
        return isMatchAbandoned;
    }

    public void setIsMatchAbandoned(Boolean isMatchAbandoned) {
        this.isMatchAbandoned = isMatchAbandoned;
    }

    public Integer getWinningTeamId() {
        return winningTeamId;
    }

    public void setWinningTeamId(Integer winningTeamId) {
        this.winningTeamId = winningTeamId;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Boolean getIsWomensMatch() {
        return isWomensMatch;
    }

    public void setIsWomensMatch(Boolean isWomensMatch) {
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

    public String getGamedayStatus() {
        return gamedayStatus;
    }

    public void setGamedayStatus(String gamedayStatus) {
        this.gamedayStatus = gamedayStatus;
    }

    public Boolean getIsGamedayEnabled() {
        return isGamedayEnabled;
    }

    public void setIsGamedayEnabled(Boolean isGamedayEnabled) {
        this.isGamedayEnabled = isGamedayEnabled;
    }

    public Boolean getRemoveMatch() {
        return removeMatch;
    }

    public void setRemoveMatch(Boolean removeMatch) {
        this.removeMatch = removeMatch;
    }

}