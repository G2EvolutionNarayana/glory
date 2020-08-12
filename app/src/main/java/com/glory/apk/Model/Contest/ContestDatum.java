package com.glory.apk.Model.Contest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContestDatum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("spots")
    @Expose
    private Integer spots;

    public Integer getSpots() {
        return spots;
    }

    public void setSpots(Integer spots) {
        this.spots = spots;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("package_id")
    @Expose
    private Integer packageId;
    @SerializedName("prize_amount")
    @Expose
    private Integer prizeAmount;
    @SerializedName("entry_fee")
    @Expose
    private Integer entryFee;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("package")
    @Expose
    private ContestPackage _Contest_package;
    @SerializedName("match")
    @Expose
    private ContestMatch contestMatch;

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

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(Integer prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public Integer getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(Integer entryFee) {
        this.entryFee = entryFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public ContestPackage getPackage() {
        return _Contest_package;
    }

    public void setPackage(ContestPackage _Contest_package) {
        this._Contest_package = _Contest_package;
    }

    public ContestMatch getContestMatch() {
        return contestMatch;
    }

    public void setContestMatch(ContestMatch contestMatch) {
        this.contestMatch = contestMatch;
    }

}
