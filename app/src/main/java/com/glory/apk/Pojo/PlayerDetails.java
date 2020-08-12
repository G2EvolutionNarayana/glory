package com.glory.apk.Pojo;

public class PlayerDetails {
    int image;
    String name,points,credits;

    public PlayerDetails(int image, String name, String points, String credits) {
        this.image = image;
        this.name = name;
        this.points = points;
        this.credits = credits;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }
}
