package com.poker.holdem.server.deserialization.gamestarts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameStartsRoomParams {
    @SerializedName("bank")
    @Expose
    private Integer bank;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("isgamerunning")
    @Expose
    //Конечно, с началом игры этот флаг будет true
    //но пока пусть будет
    private Boolean isgamerunning;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cards")
    @Expose
    private GameStartsRPCards cards;
    @SerializedName("playersingame")
    @Expose
    private List<GameStartsRPPlayer> playersingame = null;
    @SerializedName("allplayers")
    @Expose
    private List<GameStartsRPPlayer> allplayers = null;

    public Integer getBank() {
        return bank;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Boolean getIsgamerunning() {
        return isgamerunning;
    }

    public void setIsgamerunning(Boolean isgamerunning) {
        this.isgamerunning = isgamerunning;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameStartsRPCards getCards() {
        return cards;
    }

    public void setCards(GameStartsRPCards cards) {
        this.cards = cards;
    }

    public List<GameStartsRPPlayer> getPlayersingame() {
        return playersingame;
    }

    public void setPlayersingame(List<GameStartsRPPlayer> playersingame) {
        this.playersingame = playersingame;
    }

    public List<GameStartsRPPlayer> getAllplayers() {
        return allplayers;
    }

    public void setAllplayers(List<GameStartsRPPlayer> allplayers) {
        this.allplayers = allplayers;
    }
}
