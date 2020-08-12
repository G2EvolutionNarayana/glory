package com.glory.apk.Model;

import java.util.ArrayList;

public class WinningDataModel {
    String date;
    ArrayList<WinningChildDataModel> list;

    public WinningDataModel(String date, ArrayList<WinningChildDataModel> list) {
        this.date = date;
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<WinningChildDataModel> getList() {
        return list;
    }

    public void setList(ArrayList<WinningChildDataModel> list) {
        this.list = list;
    }
}
