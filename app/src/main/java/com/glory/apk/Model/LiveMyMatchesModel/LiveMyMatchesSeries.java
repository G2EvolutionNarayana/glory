package com.glory.apk.Model.LiveMyMatchesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveMyMatchesSeries {
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
    private Object shortName;
    @SerializedName("statisticsProvider")
    @Expose
    private String statisticsProvider;
    @SerializedName("shieldImageUrl")
    @Expose
    private String shieldImageUrl;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("startDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("endDateTime")
    @Expose
    private String endDateTime;
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

    public Object getShortName() {
        return shortName;
    }

    public void setShortName(Object shortName) {
        this.shortName = shortName;
    }

    public String getStatisticsProvider() {
        return statisticsProvider;
    }

    public void setStatisticsProvider(String statisticsProvider) {
        this.statisticsProvider = statisticsProvider;
    }

    public String getShieldImageUrl() {
        return shieldImageUrl;
    }

    public void setShieldImageUrl(String shieldImageUrl) {
        this.shieldImageUrl = shieldImageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
