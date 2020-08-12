package com.glory.apk.Model;

public class HomePlayersSelected {
    private String name;
    private String image;
    private int id;
    private Boolean isSelected;


    public HomePlayersSelected(String name, String image, int id, Boolean isSelected) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.isSelected = isSelected;
    }

    public Boolean getSelected() {

        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
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
