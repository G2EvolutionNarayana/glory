package com.glory.apk.Model.EditTeamListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditTeamExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private EditTeamResponse editTeamResponse;
    @SerializedName("data")
    @Expose
    private List<EditTeamDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private EditTeamFilters editTeamFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EditTeamResponse getEditTeamResponse() {
        return editTeamResponse;
    }

    public void setEditTeamResponse(EditTeamResponse editTeamResponse) {
        this.editTeamResponse = editTeamResponse;
    }

    public List<EditTeamDatum> getData() {
        return data;
    }

    public void setData(List<EditTeamDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public EditTeamFilters getEditTeamFilters() {
        return editTeamFilters;
    }

    public void setEditTeamFilters(EditTeamFilters editTeamFilters) {
        this.editTeamFilters = editTeamFilters;
    }
}
