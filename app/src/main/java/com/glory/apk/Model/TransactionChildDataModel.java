package com.glory.apk.Model;

public class TransactionChildDataModel {
    String trans_code;
    String trans_amount;
    String trans_type;
    String trans_status;

    public TransactionChildDataModel(String trans_code, String trans_amount, String trans_type, String trans_status) {
        this.trans_code = trans_code;
        this.trans_amount = trans_amount;
        this.trans_type = trans_type;
        this.trans_status = trans_status;
    }

    public String getTrans_code() {
        return trans_code;
    }

    public void setTrans_code(String trans_code) {
        this.trans_code = trans_code;
    }

    public String getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(String trans_amount) {
        this.trans_amount = trans_amount;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getTrans_status() {
        return trans_status;
    }

    public void setTrans_status(String trans_status) {
        this.trans_status = trans_status;
    }
}
