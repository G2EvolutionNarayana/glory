package com.glory.apk.Model.NotificationModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private NotificationResponse notificationResponse;
    @SerializedName("data")
    @Expose
    private List<NotificationModelDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private NotificationFilters notificationFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NotificationResponse getNotificationResponse() {
        return notificationResponse;
    }

    public void setNotificationResponse(NotificationResponse notificationResponse) {
        this.notificationResponse = notificationResponse;
    }

    public List<NotificationModelDatum> getData() {
        return data;
    }

    public void setData(List<NotificationModelDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public NotificationFilters getNotificationFilters() {
        return notificationFilters;
    }

    public void setNotificationFilters(NotificationFilters notificationFilters) {
        this.notificationFilters = notificationFilters;
    }
}
