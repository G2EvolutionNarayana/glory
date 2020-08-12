package com.glory.apk.Retrofit.Login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("modified_datetime")
    @Expose
    private String modifiedDatetime;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("reset_pwd_hash")
    @Expose
    private Object resetPwdHash;
    @SerializedName("reset_pwd_status")
    @Expose
    private String resetPwdStatus;
    @SerializedName("api_token")
    @Expose
    private Object apiToken;
    @SerializedName("user_otp")
    @Expose
    private String userOtp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedDatetime() {
        return modifiedDatetime;
    }

    public void setModifiedDatetime(String modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getResetPwdHash() {
        return resetPwdHash;
    }

    public void setResetPwdHash(Object resetPwdHash) {
        this.resetPwdHash = resetPwdHash;
    }

    public String getResetPwdStatus() {
        return resetPwdStatus;
    }

    public void setResetPwdStatus(String resetPwdStatus) {
        this.resetPwdStatus = resetPwdStatus;
    }

    public Object getApiToken() {
        return apiToken;
    }

    public void setApiToken(Object apiToken) {
        this.apiToken = apiToken;
    }

    public String getUserOtp() {
        return userOtp;
    }

    public void setUserOtp(String userOtp) {
        this.userOtp = userOtp;
    }

}