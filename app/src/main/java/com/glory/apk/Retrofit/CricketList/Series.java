package com.glory.apk.Retrofit.CricketList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Series {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("shieldImageUrl")
    @Expose
    private String shieldImageUrl;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShieldImageUrl() {
        return shieldImageUrl;
    }

    public void setShieldImageUrl(String shieldImageUrl) {
        this.shieldImageUrl = shieldImageUrl;
    }

}