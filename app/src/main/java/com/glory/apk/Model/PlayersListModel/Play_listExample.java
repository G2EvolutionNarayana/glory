package com.glory.apk.Model.PlayersListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Play_listExample {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("data")
    @Expose
    private List<Player__ListDatum> data = null;
    @SerializedName("pagination")
    @Expose
    private List<Object> pagination = null;
    @SerializedName("filters")
    @Expose
    private Play_listFilters playlistFilters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<Player__ListDatum> getData() {
        return data;
    }

    public void setData(List<Player__ListDatum> data) {
        this.data = data;
    }

    public List<Object> getPagination() {
        return pagination;
    }

    public void setPagination(List<Object> pagination) {
        this.pagination = pagination;
    }

    public Play_listFilters getPlaylistFilters() {
        return playlistFilters;
    }

    public void setPlaylistFilters(Play_listFilters playlistFilters) {
        this.playlistFilters = playlistFilters;
    }
}
