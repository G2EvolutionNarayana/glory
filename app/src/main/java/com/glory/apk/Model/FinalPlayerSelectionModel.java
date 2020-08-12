package com.glory.apk.Model;

import java.io.Serializable;

public class FinalPlayerSelectionModel implements Serializable {
    private String name;
    private String image;
    private int id;
    private String PlayerType;

    public FinalPlayerSelectionModel(String name, String image, int id,String PlayerType) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.PlayerType=PlayerType;
    }

    public String getPlayerType() {
        return PlayerType;
    }

    public void setPlayerType(String playerType) {
        PlayerType = playerType;
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
