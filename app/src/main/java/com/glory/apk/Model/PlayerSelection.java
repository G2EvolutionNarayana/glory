package com.glory.apk.Model;

public class PlayerSelection {
    private Boolean isSelected;
    private int homeTeamSelected;
    private int oppositeTeamSelected;


    public PlayerSelection(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
