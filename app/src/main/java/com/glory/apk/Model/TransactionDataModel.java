package com.glory.apk.Model;

import java.util.ArrayList;

public class TransactionDataModel {
    String date;
    ArrayList<TransactionChildDataModel> list;

    public TransactionDataModel(String date, ArrayList<TransactionChildDataModel> list) {
        this.date = date;
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<TransactionChildDataModel> getList() {
        return list;
    }

    public void setList(ArrayList<TransactionChildDataModel> list) {
        this.list = list;
    }
}
