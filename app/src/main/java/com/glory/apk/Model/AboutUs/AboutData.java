package com.glory.apk.Model.AboutUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("uuid")
    @Expose
    private String uuid;

    @SerializedName("gender")
    @Expose
    private Object  gender;

    @SerializedName("dob")
    @Expose
    private Object  dob;

    public Object  getGender() {
        return gender;
    }

    public void setGender(Object  gender) {
        this.gender = gender;
    }

    public Object  getDob() {
        return dob;
    }

    public void setDob(Object  dob) {
        this.dob = dob;
    }

    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("fcm_token")
    @Expose
    private Object fcmToken;
    @SerializedName("device_id")
    @Expose
    private Object deviceId;
    @SerializedName("device_type")
    @Expose
    private Object deviceType;
    @SerializedName("google_id")
    @Expose
    private Object googleId;
    @SerializedName("fb_id")
    @Expose
    private Object fbId;
    @SerializedName("linkedin_id")
    @Expose
    private Object linkedinId;
    @SerializedName("register_exp")
    @Expose
    private Object registerExp;
    @SerializedName("forgot_exp")
    @Expose
    private Object forgotExp;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("wallet_amount")
    @Expose
    private Double walletAmount;
    @SerializedName("referrel_code")
    @Expose
    private Object referrelCode;
    @SerializedName("account_status")
    @Expose
    private String accountStatus;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;


    @SerializedName("winning_amount")
    @Expose
    private String winning_amount;

    @SerializedName("deposited_amount")
    @Expose
    private String deposited_amount;

    public String getDeposited_amount() {
        return deposited_amount;
    }

    public void setDeposited_amount(String deposited_amount) {
        this.deposited_amount = deposited_amount;
    }

    public String getWinning_amount() {
        return winning_amount;
    }

    public void setWinning_amount(String winning_amount) {
        this.winning_amount = winning_amount;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(Object fcmToken) {
        this.fcmToken = fcmToken;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public Object getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Object deviceType) {
        this.deviceType = deviceType;
    }

    public Object getGoogleId() {
        return googleId;
    }

    public void setGoogleId(Object googleId) {
        this.googleId = googleId;
    }

    public Object getFbId() {
        return fbId;
    }

    public void setFbId(Object fbId) {
        this.fbId = fbId;
    }

    public Object getLinkedinId() {
        return linkedinId;
    }

    public void setLinkedinId(Object linkedinId) {
        this.linkedinId = linkedinId;
    }

    public Object getRegisterExp() {
        return registerExp;
    }

    public void setRegisterExp(Object registerExp) {
        this.registerExp = registerExp;
    }

    public Object getForgotExp() {
        return forgotExp;
    }

    public void setForgotExp(Object forgotExp) {
        this.forgotExp = forgotExp;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(Double walletAmount) {
        this.walletAmount = walletAmount;
    }

    public Object getReferrelCode() {
        return referrelCode;
    }

    public void setReferrelCode(Object referrelCode) {
        this.referrelCode = referrelCode;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
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
}
