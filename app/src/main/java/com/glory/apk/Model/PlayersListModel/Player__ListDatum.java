package com.glory.apk.Model.PlayersListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Player__ListDatum {
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
    @SerializedName("selected_users")
    @Expose
    private List<SelectedUser> selectedUsers = null;
    @SerializedName("match")
    @Expose
    private Play_listMatch playlistMatch;

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

    public List<SelectedUser> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(List<SelectedUser> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public Play_listMatch getPlaylistMatch() {
        return playlistMatch;
    }

    public void setPlaylistMatch(Play_listMatch playlistMatch) {
        this.playlistMatch = playlistMatch;
    }

}
