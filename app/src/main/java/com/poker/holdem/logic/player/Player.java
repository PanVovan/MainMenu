package com.poker.holdem.logic.player;

import java.util.ArrayList;
import java.util.List;

public class Player{

    private String name;
    private int money;
    private int numOfPicture;
    private boolean active;
    private List<Integer> cards = new ArrayList<>();
    private int pos;
    //сделано для отсчёта раундов:
    //когда сходили все игроки в комнате,
    //раунд сменяется и открывается новая карта
    //private boolean didSomethingInThisRound;

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Integer> getCards() { return cards; }

    public void setCards(List<Integer> cards) { this.cards = cards; }

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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    //public boolean isDidSomethingInThisRound() {
    //    return didSomethingInThisRound;
    //}
//
    //public void setDidSomethingInThisRound(boolean didSomethingInThisRound) {
    //    this.didSomethingInThisRound = didSomethingInThisRound;
    //}
}
