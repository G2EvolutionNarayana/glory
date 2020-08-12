package com.glory.apk.Model.CompletedDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
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
    private Integer pairId;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("match_draw")
    @Expose
    private Integer matchDraw;
    @SerializedName("win")
    @Expose
    private Integer win;
    @SerializedName("team1_points")
    @Expose
    private Integer team1Points;
    @SerializedName("team2_points")
    @Expose
    private Integer team2Points;
    @SerializedName("pair")
    @Expose
    private Pair pair;
    @SerializedName("package")
    @Expose
    private Package _package;
    @SerializedName("contest")
    @Expose
    private Contest contest;
    @SerializedName("contest_points")
    @Expose
    private Object contestPoints;
    @SerializedName("match")
    @Expose
    private Match match;

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

    public Integer getPairId() {
        return pairId;
    }

    public void setPairId(Integer pairId) {
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

    public Integer getMatchDraw() {
        return matchDraw;
    }

    public void setMatchDraw(Integer matchDraw) {
        this.matchDraw = matchDraw;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
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

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public Package getPackage() {
        return _package;
    }

    public void setPackage(Package _package) {
        this._package = _package;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public Object getContestPoints() {
        return contestPoints;
    }

    public void setContestPoints(Object contestPoints) {
        this.contestPoints = contestPoints;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
