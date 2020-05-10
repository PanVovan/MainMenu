package com.poker.holdem.players.utils;


import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
    private String name;
    private int money;
    private int numOfPicture;
    private int pos;

    private List<Integer> cards;

    public List<Integer> getCards() {
        return cards;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

}
