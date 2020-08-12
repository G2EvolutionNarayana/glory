package com.glory.apk.Model.FinalWithDrawAmount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("rem_winning_amount")
    @Expose
    private String remWinningAmount;
    @SerializedName("rem_deposited_amount")
    @Expose
    private String remDepositedAmount;
    @SerializedName("total_wallet_balance")
    @Expose
    private String total_wallet_balance;

    public String getTotal_wallet_balance() {
        return total_wallet_balance;
    }

    public void setTotal_wallet_balance(String total_wallet_balance) {
        this.total_wallet_balance = total_wallet_balance;
    }

    public String getRemWinningAmount() {
        return remWinningAmount;
    }

    public void setRemWinningAmount(String remWinningAmount) {
        this.remWinningAmount = remWinningAmount;
    }

    public String getRemDepositedAmount() {
        return remDepositedAmount;
    }

    public void setRemDepositedAmount(String remDepositedAmount) {
        this.remDepositedAmount = remDepositedAmount;
    }
}
