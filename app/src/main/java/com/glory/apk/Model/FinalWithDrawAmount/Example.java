package com.glory.apk.Model.FinalWithDrawAmount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private Response response;

    @SerializedName("data")
    @Expose
    private Data data;


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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
