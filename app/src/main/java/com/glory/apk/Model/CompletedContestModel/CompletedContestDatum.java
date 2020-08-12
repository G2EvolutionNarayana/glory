package com.glory.apk.Model.CompletedContestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompletedContestDatum {
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
    @SerializedName("win")
    @Expose
    private Integer win;

    @SerializedName("match_draw")
    @Expose
    private Integer match_draw;

    @SerializedName("team2_points")
    @Expose
    private Integer team2_points;

    public Integer getTeam2_points() {
        return team2_points;
    }

    public void setTeam2_points(Integer team2_points) {
        this.team2_points = team2_points;
    }

    public Integer getMatch_draw() {
        return match_draw;
    }

    public void setMatch_draw(Integer match_draw) {
        this.match_draw = match_draw;
    }

    @SerializedName("is_declared")
    @Expose
    private Integer isDeclared;
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
    @SerializedName("pair")
    @Expose
    private CompletedContestPair pair;

    public CompletedContestPair getPair() {
        return pair;
    }

    public void setPair(CompletedContestPair pair) {
        this.pair = pair;
    }

    @SerializedName("package")
    @Expose
    private CompletedContestPackage _CompletedContest_package;
    @SerializedName("contest")
    @Expose
    private CompletedContestContest completedContestContest;

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

    public Integer getWin() {
        return win;
    }


    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getIsDeclared() {
        return isDeclared;
    }

    public void setIsDeclared(Integer isDeclared) {
        this.isDeclared = isDeclared;
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



    public CompletedContestPackage getPackage() {
        return _CompletedContest_package;
    }

    public void setPackage(CompletedContestPackage _CompletedContest_package) {
        this._CompletedContest_package = _CompletedContest_package;
    }

    public CompletedContestContest getCompletedContestContest() {
        return completedContestContest;
    }

    public void setCompletedContestContest(CompletedContestContest completedContestContest) {
        this.completedContestContest = completedContestContest;
    }

}
