package com.android.glory.Model.MyMatchesUpComing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyMatchesUpComingDatetime {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("startDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("endDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("localStartDate")
    @Expose
    private Object localStartDate;
    @SerializedName("localStartTime")
    @Expose
    private Object localStartTime;
    @SerializedName("localTimezone")
    @Expose
    private Object localTimezone;
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

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
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

    public Object getLocalStartDate() {
        return localStartDate;
    }

    public void setLocalStartDate(Object localStartDate) {
        this.localStartDate = localStartDate;
    }

    public Object getLocalStartTime() {
        return localStartTime;
    }

    public void setLocalStartTime(Object localStartTime) {
        this.localStartTime = localStartTime;
    }

    public Object getLocalTimezone() {
        return localTimezone;
    }

    public void setLocalTimezone(Object localTimezone) {
        this.localTimezone = localTimezone;
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
