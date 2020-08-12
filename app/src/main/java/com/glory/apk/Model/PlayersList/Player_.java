package com.glory.apk.Model.PlayersList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player_ {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("playerId")
    @Expose
    private Integer playerId;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("imageURL")
    @Expose
    private Object imageURL;
    @SerializedName("battingStyle")
    @Expose
    private Object battingStyle;
    @SerializedName("bowlingStyle")
    @Expose
    private Object bowlingStyle;
    @SerializedName("playerType")
    @Expose
    private Object playerType;
    @SerializedName("dob")
    @Expose
    private Object dob;
    @SerializedName("testDebutDate")
    @Expose
    private Object testDebutDate;
    @SerializedName("odiDebutDate")
    @Expose
    private Object odiDebutDate;
    @SerializedName("t20DebutDate")
    @Expose
    private Object t20DebutDate;
    @SerializedName("bio")
    @Expose
    private Object bio;
    @SerializedName("didYouKnow")
    @Expose
    private Object didYouKnow;
    @SerializedName("height")
    @Expose
    private Object height;
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

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getImageURL() {
        return imageURL;
    }

    public void setImageURL(Object imageURL) {
        this.imageURL = imageURL;
    }

    public Object getBattingStyle() {
        return battingStyle;
    }

    public void setBattingStyle(Object battingStyle) {
        this.battingStyle = battingStyle;
    }

    public Object getBowlingStyle() {
        return bowlingStyle;
    }

    public void setBowlingStyle(Object bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    public Object getPlayerType() {
        return playerType;
    }

    public void setPlayerType(Object playerType) {
        this.playerType = playerType;
    }

    public Object getDob() {
        return dob;
    }

    public void setDob(Object dob) {
        this.dob = dob;
    }

    public Object getTestDebutDate() {
        return testDebutDate;
    }

    public void setTestDebutDate(Object testDebutDate) {
        this.testDebutDate = testDebutDate;
    }

    public Object getOdiDebutDate() {
        return odiDebutDate;
    }

    public void setOdiDebutDate(Object odiDebutDate) {
        this.odiDebutDate = odiDebutDate;
    }

    public Object getT20DebutDate() {
        return t20DebutDate;
    }

    public void setT20DebutDate(Object t20DebutDate) {
        this.t20DebutDate = t20DebutDate;
    }

    public Object getBio() {
        return bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public Object getDidYouKnow() {
        return didYouKnow;
    }

    public void setDidYouKnow(Object didYouKnow) {
        this.didYouKnow = didYouKnow;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(Object height) {
        this.height = height;
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
