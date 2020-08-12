package com.glory.apk.Retrofit.CricketList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("matchList")
    @Expose
    private MatchList matchList;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("poweredBy")
    @Expose
    private String poweredBy;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public MatchList getMatchList() {
        return matchList;
    }

    public void setMatchList(MatchList matchList) {
        this.matchList = matchList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPoweredBy() {
        return poweredBy;
    }

    public void setPoweredBy(String poweredBy) {
        this.poweredBy = poweredBy;
    }

}