package com.glory.apk.Model;

public class OpposPlayerSelected {
    String name;
    String image;
    int id;
   Boolean isSelected;

    public OpposPlayerSelected(String name, String image, int id,Boolean isSelected) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.isSelected=isSelected;
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

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
