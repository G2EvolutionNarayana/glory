package com.glory.apk.Model.WithdrawalList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("requested_on")
    @Expose
    private String requestedOn;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("reject_reason")
    @Expose
    private Object rejectReason;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;
    @SerializedName("process_by")
    @Expose
    private Object processBy;
    @SerializedName("process_on")
    @Expose
    private Object processOn;
    @SerializedName("processed_amt")
    @Expose
    private String processedAmt;
    @SerializedName("reference_number")
    @Expose
    private String referenceNumber;
    @SerializedName("payment_mode")
    @Expose
    private Object paymentMode;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(String requestedOn) {
        this.requestedOn = requestedOn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(Object rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Object getProcessBy() {
        return processBy;
    }

    public void setProcessBy(Object processBy) {
        this.processBy = processBy;
    }

    public Object getProcessOn() {
        return processOn;
    }

    public void setProcessOn(Object processOn) {
        this.processOn = processOn;
    }

    public String getProcessedAmt() {
        return processedAmt;
    }

    public void setProcessedAmt(String processedAmt) {
        this.processedAmt = processedAmt;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Object getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Object paymentMode) {
        this.paymentMode = paymentMode;
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
