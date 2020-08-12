package com.glory.apk.Model.WithdrawVerificationModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("email")
    @Expose
    private Email email;
    @SerializedName("phone")
    @Expose
    private Phone phone;
    @SerializedName("pancard")
    @Expose
    private Pancard pancard;
    @SerializedName("bank_details")
    @Expose
    private BankDetails bankDetails;

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Pancard getPancard() {
        return pancard;
    }

    public void setPancard(Pancard pancard) {
        this.pancard = pancard;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

}
