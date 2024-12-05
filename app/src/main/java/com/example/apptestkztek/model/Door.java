package com.example.apptestkztek.model;

public class Door {
    private String doorNumber;
    private boolean isOpenDoor;

    public Door(String doorNumber, boolean isOpenDoor) {
        this.doorNumber = doorNumber;
        this.isOpenDoor = isOpenDoor;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public boolean isOpenDoor() {
        return isOpenDoor;
    }

    public void setOpenDoor(boolean openDoor) {
        isOpenDoor = openDoor;
    }
}
