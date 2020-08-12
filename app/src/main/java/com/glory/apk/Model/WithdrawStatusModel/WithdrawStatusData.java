package com.glory.apk.Model.WithdrawStatusModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawStatusData {
    @SerializedName("email")
    @Expose
    private WithdrawStatusEmail email;
    @SerializedName("phone")
    @Expose
    private WithdrawStatusPhone phone;
    @SerializedName("pancard")
    @Expose
    private Object pancard;

    public WithdrawStatusEmail getEmail() {
        return email;
    }

    public void setEmail(WithdrawStatusEmail email) {
        this.email = email;
    }

    public WithdrawStatusPhone getPhone() {
        return phone;
    }

    public void setPhone(WithdrawStatusPhone phone) {
        this.phone = phone;
    }

    public Object getPancard() {
        return pancard;
    }

    public void setPancard(Object pancard) {
        this.pancard = pancard;
    }
}
