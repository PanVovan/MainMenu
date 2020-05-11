package com.poker.holdem.server.deserialization.restore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RestoreRPCards {
    @SerializedName("deck")
    @Expose
    private List<Integer> deck =  new ArrayList<>();
    @SerializedName("players")
    @Expose
    private List<RestoreRPCardsPlayer> players =  new ArrayList<>();

    public List<Integer> getDeck() {
        return deck;
    }

    public void setDeck(List<Integer> deck) {
        this.deck = deck;
    }

    public List<RestoreRPCardsPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<RestoreRPCardsPlayer> players) {
        this.players = players;
    }
}
