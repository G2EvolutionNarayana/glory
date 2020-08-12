package com.glory.apk.Model.LiveContestListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveContestPackage {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image_url")
    @Expose
    private Object imageUrl;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("limit_per_team")
    @Expose
    private Integer limitPerTeam;
    @SerializedName("status")
    @Expose
    private Object status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimitPerTeam() {
        return limitPerTeam;
    }

    public void setLimitPerTeam(Integer limitPerTeam) {
        this.limitPerTeam = limitPerTeam;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
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
