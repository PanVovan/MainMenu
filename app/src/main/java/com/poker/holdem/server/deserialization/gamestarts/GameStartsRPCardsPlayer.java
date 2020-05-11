package com.poker.holdem.server.deserialization.gamestarts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GameStartsRPCardsPlayer {
    @SerializedName("playername")
    @Expose
    private String playername;
    @SerializedName("cards")
    @Expose
    private List<Integer> cards =  new ArrayList<>();

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

}
