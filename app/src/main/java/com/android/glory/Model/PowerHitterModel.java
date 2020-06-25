package com.android.glory.Model;

public class PowerHitterModel {
    String name;
    String image;
    int id;
    Boolean powerHitter;

    public PowerHitterModel(String name, String image, int id, Boolean powerHitter) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.powerHitter = powerHitter;
    }

    public Boolean getPowerHitter() {
        return powerHitter;
    }

    public void setPowerHitter(Boolean powerHitter) {
        this.powerHitter = powerHitter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

