package com.glory.apk.Model.AboutUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutExample {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private AboutResponse aboutResponse;
    @SerializedName("data")
    @Expose
    private AboutData aboutData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AboutResponse getAboutResponse() {
        return aboutResponse;
    }

    public void setAboutResponse(AboutResponse aboutResponse) {
        this.aboutResponse = aboutResponse;
    }

    public AboutData getAboutData() {
        return aboutData;
    }

    public void setAboutData(AboutData aboutData) {
        this.aboutData = aboutData;
    }
}
