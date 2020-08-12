package com.glory.apk.Model;

public class DepositedChildModel {
    String trans_amount;
    String order_id;
    String bank_ref_no;
    String payment_mode;
    String Pamentstatus;

    public DepositedChildModel(String trans_amount, String order_id, String bank_ref_no, String payment_mode, String pamentstatus) {
        this.trans_amount = trans_amount;
        this.order_id = order_id;
        this.bank_ref_no = bank_ref_no;
        this.payment_mode = payment_mode;
        Pamentstatus = pamentstatus;
    }

    public String getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(String trans_amount) {
        this.trans_amount = trans_amount;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getBank_ref_no() {
        return bank_ref_no;
    }

    public void setBank_ref_no(String bank_ref_no) {
        this.bank_ref_no = bank_ref_no;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getPamentstatus() {
        return Pamentstatus;
    }

    public void setPamentstatus(String pamentstatus) {
        Pamentstatus = pamentstatus;
    }
}
