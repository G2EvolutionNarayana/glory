package com.glory.apk.Model.EditPlayersListModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditPlayerFilters {

    @SerializedName("contest_user_id")
    @Expose
    private String contestUserId;

    public String getContestUserId() {
        return contestUserId;
    }

    public void setContestUserId(String contestUserId) {
        this.contestUserId = contestUserId;
    }

}
