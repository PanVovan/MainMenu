package com.poker.holdem.logic.player;

public class Player{
    private String name;
    private int money;
    private int numOfPicture;
    private boolean active;
    private int[] cards;

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNumOfPicture() {
        return numOfPicture;
    }

    public void setNumOfPicture(int numOfPicture) {
        this.numOfPicture = numOfPicture;
    }


}
