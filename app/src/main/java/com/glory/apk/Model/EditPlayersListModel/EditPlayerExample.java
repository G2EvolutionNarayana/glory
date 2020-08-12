package com.glory.apk.Model.EditPlayersListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditPlayerExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private EditPlayerResponse editPlayerResponse;
    @SerializedName("data")
    @Expose
    private List<EditPlayerDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private EditPlayerFilters editPlayerFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EditPlayerResponse getEditPlayerResponse() {
        return editPlayerResponse;
    }

    public void setEditPlayerResponse(EditPlayerResponse editPlayerResponse) {
        this.editPlayerResponse = editPlayerResponse;
    }

    public List<EditPlayerDatum> getData() {
        return data;
    }

    public void setData(List<EditPlayerDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public EditPlayerFilters getEditPlayerFilters() {
        return editPlayerFilters;
    }

    public void setEditPlayerFilters(EditPlayerFilters editPlayerFilters) {
        this.editPlayerFilters = editPlayerFilters;
    }

}
