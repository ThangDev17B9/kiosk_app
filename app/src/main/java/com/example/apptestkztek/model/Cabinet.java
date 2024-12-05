package com.example.apptestkztek.model;

public class Cabinet {
    private String name;
    private final Boolean isOpen;

    public Cabinet(String name, Boolean isOpen) {
        this.name = name;
        this.isOpen = isOpen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return isOpen;
    }

}
