package com.glory.apk.Model;

public class AccountListModel {
    int image;
    String accountNumber;
    String BankName;

    public AccountListModel(int image, String accountNumber, String bankName) {
        this.image = image;
        this.accountNumber = accountNumber;
        BankName = bankName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }
}
