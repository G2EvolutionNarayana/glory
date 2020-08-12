package com.glory.apk.Model.MyMatches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Series {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("series_id")
    @Expose
    private Integer seriesId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("statisticsProvider")
    @Expose
    private Object statisticsProvider;
    @SerializedName("shieldImageUrl")
    @Expose
    private String shieldImageUrl;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("startDateTime")
    @Expose
    private Object startDateTime;
    @SerializedName("endDateTime")
    @Expose
    private Object endDateTime;
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

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Object getStatisticsProvider() {
        return statisticsProvider;
    }

    public void setStatisticsProvider(Object statisticsProvider) {
        this.statisticsProvider = statisticsProvider;
    }

    public String getShieldImageUrl() {
        return shieldImageUrl;
    }

    public void setShieldImageUrl(String shieldImageUrl) {
        this.shieldImageUrl = shieldImageUrl;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Object startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Object getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Object endDateTime) {
        this.endDateTime = endDateTime;
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