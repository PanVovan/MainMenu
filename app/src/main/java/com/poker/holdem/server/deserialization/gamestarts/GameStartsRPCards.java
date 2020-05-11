package com.poker.holdem.server.deserialization.gamestarts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GameStartsRPCards {
    @SerializedName("deck")
    @Expose
    private List<Integer> deck =  new ArrayList<>();
    @SerializedName("players")
    @Expose
    private List<GameStartsRPCardsPlayer> players = new ArrayList<>();

    public List<Integer> getDeck() {
        return deck;
    }

    public void setDeck(List<Integer> deck) {
        this.deck = deck;
    }

    public List<GameStartsRPCardsPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<GameStartsRPCardsPlayer> players) {
        this.players = players;
    }
}
