package com.poker.holdem.server.deserialization.restore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RestoreRoomParams {
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
    private RestoreRPCards cards;
    @SerializedName("playersingame")
    @Expose
    private List<RestoreRPPlayer> playersingame =  new ArrayList<>();
    @SerializedName("allplayers")
    @Expose
    private List<RestoreRPPlayer> allplayers =  new ArrayList<>();

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

    public RestoreRPCards getCards() {
        return cards;
    }

    public void setCards(RestoreRPCards cards) {
        this.cards = cards;
    }

    public List<RestoreRPPlayer> getPlayersingame() {
        return playersingame;
    }

    public void setPlayersingame(List<RestoreRPPlayer> playersingame) {
        this.playersingame = playersingame;
    }

    public List<RestoreRPPlayer> getAllplayers() {
        return allplayers;
    }

    public void setAllplayers(List<RestoreRPPlayer> allplayers) {
        this.allplayers = allplayers;
    }
}
