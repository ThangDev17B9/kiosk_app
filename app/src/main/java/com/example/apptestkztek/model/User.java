package com.example.apptestkztek.model;

public class User {
    private int userId;
    private int lenCard;
    private int card;
    private int pin;
    private int mode;
    private int timeZone;
    private String door;

    public User() {
    }

    public User(int userId, int lenCard, int card, int pin, int mode, int timeZone, String door) {
        this.userId = userId;
        this.lenCard = lenCard;
        this.card = card;
        this.pin = pin;
        this.mode = mode;
        this.timeZone = timeZone;
        this.door = door;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLenCard() {
        return lenCard;
    }

    public void setLenCard(int lenCard) {
        this.lenCard = lenCard;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }
}
