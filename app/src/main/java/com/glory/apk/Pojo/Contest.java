package com.glory.apk.Pojo;

public class Contest {
    String prize,entryFee,spotsLeft,spots;

    public Contest(String prize, String entryFee, String spotsLeft, String spots) {
        this.prize = prize;
        this.entryFee = entryFee;
        this.spotsLeft = spotsLeft;
        this.spots = spots;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(String entryFee) {
        this.entryFee = entryFee;
    }

    public String getSpotsLeft() {
        return spotsLeft;
    }

    public void setSpotsLeft(String spotsLeft) {
        this.spotsLeft = spotsLeft;
    }

    public String getSpots() {
        return spots;
    }

    public void setSpots(String spots) {
        this.spots = spots;
    }
}
