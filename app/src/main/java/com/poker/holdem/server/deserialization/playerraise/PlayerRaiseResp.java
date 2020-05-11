package com.poker.holdem.server.deserialization.playerraise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerRaiseResp {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lobbyname")
    @Expose
    private String newlead;
    @SerializedName("rate")
    @Expose
    private Integer rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewlead() {
        return newlead;
    }

    public void setNewlead(String newlead) {
        this.newlead = newlead;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
