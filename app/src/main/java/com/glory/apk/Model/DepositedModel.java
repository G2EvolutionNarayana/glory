package com.glory.apk.Model;

import java.util.ArrayList;

public class DepositedModel {
    String date;
    ArrayList<DepositedChildModel> list;

    public DepositedModel(String date, ArrayList<DepositedChildModel> list) {
        this.date = date;
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<DepositedChildModel> getList() {
        return list;
    }

    public void setList(ArrayList<DepositedChildModel> list) {
        this.list = list;
    }
}
