package com.poker.holdem.server.deserialization.enterlobby;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LobbyInfoResp {

    @SerializedName("bank")
    @Expose
    private Integer bank;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("isgamerunning")
    @Expose
    private Boolean isgamerunning;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cards")
    @Expose
    private LobbyCardsResp cards;
    @SerializedName("playersingame")
    @Expose
    private List<LobbyPlayerResp> playersingame =  new ArrayList<>();
    @SerializedName("allplayers")
    @Expose
    private List<LobbyPlayerResp> allplayers = new ArrayList<>();
    @SerializedName("lead")
    @Expose
    private String lead;

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

    public LobbyCardsResp getCards() {
        return cards;
    }

    public void setCards(LobbyCardsResp cards) {
        this.cards = cards;
    }

    public List<LobbyPlayerResp> getPlayersingame() {
        return playersingame;
    }

    public void setPlayersingame(List<LobbyPlayerResp> playersingame) {
        this.playersingame = playersingame;
    }

    public List<LobbyPlayerResp> getAllplayers() {
        return allplayers;
    }

    public void setAllplayers(List<LobbyPlayerResp> allplayers) {
        this.allplayers = allplayers;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }
}
