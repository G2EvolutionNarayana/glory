package com.glory.apk.Retrofit.CricketList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwayTeam {

    @SerializedName("isBatting")
    @Expose
    private Boolean isBatting;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("logoUrl")
    @Expose
    private String logoUrl;
    @SerializedName("backgroundImageUrl")
    @Expose
    private String backgroundImageUrl;
    @SerializedName("teamColour")
    @Expose
    private String teamColour;

    public Boolean getIsBatting() {
        return isBatting;
    }

    public void setIsBatting(Boolean isBatting) {
        this.isBatting = isBatting;
    }

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public String getTeamColour() {
        return teamColour;
    }

    public void setTeamColour(String teamColour) {
        this.teamColour = teamColour;
    }

}