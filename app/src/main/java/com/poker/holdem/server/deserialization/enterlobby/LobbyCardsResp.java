package com.poker.holdem.server.deserialization.enterlobby;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LobbyCardsResp {
    @SerializedName("deck")
    @Expose
    private List<Integer> deck =  new ArrayList<>();
    @SerializedName("players")
    @Expose
    private List<LobbyInfoPlayerCardsResp> players =  new ArrayList<>();

    public List<Integer> getDeck() {
        return deck;
    }

    public void setDeck(List<Integer> deck) {
        this.deck = deck;
    }

    public List<LobbyInfoPlayerCardsResp> getPlayers() {
        return players;
    }

    public void setPlayers(List<LobbyInfoPlayerCardsResp> players) {
        this.players = players;
    }
}
