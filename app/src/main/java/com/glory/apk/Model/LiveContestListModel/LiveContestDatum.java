package com.glory.apk.Model.LiveContestListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveContestDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("match_id")
    @Expose
    private Integer matchId;
    @SerializedName("package_id")
    @Expose
    private Integer packageId;
    @SerializedName("contest_id")
    @Expose
    private Integer contestId;
    @SerializedName("payment_status")
    @Expose
    private Object paymentStatus;
    @SerializedName("transaction_id")
    @Expose
    private Object transactionId;
    @SerializedName("pair_id")
    @Expose
    private Object pairId;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("team1_points")
    @Expose
    private Integer team1Points;
    @SerializedName("team2_points")
    @Expose
    private Integer team2Points;
    @SerializedName("pair")
    @Expose
    private LiveContestPair liveContestPair;
    @SerializedName("package")
    @Expose
    private LiveContestPackage _LiveContest_package;
    @SerializedName("contest")
    @Expose
    private LiveContestContest liveContestContest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Object getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Object paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Object getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Object transactionId) {
        this.transactionId = transactionId;
    }

    public Object getPairId() {
        return pairId;
    }

    public void setPairId(Object pairId) {
        this.pairId = pairId;
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

    public Integer getTeam1Points() {
        return team1Points;
    }

    public void setTeam1Points(Integer team1Points) {
        this.team1Points = team1Points;
    }

    public Integer getTeam2Points() {
        return team2Points;
    }

    public void setTeam2Points(Integer team2Points) {
        this.team2Points = team2Points;
    }

    public LiveContestPair getLiveContestPair() {
        return liveContestPair;
    }

    public void setLiveContestPair(LiveContestPair liveContestPair) {
        this.liveContestPair = liveContestPair;
    }

    public LiveContestPackage getPackage() {
        return _LiveContest_package;
    }

    public void setPackage(LiveContestPackage _LiveContest_package) {
        this._LiveContest_package = _LiveContest_package;
    }

    public LiveContestContest getLiveContestContest() {
        return liveContestContest;
    }

    public void setLiveContestContest(LiveContestContest liveContestContest) {
        this.liveContestContest = liveContestContest;
    }
}
