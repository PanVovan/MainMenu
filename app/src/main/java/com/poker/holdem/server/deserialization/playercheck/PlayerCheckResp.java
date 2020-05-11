package com.poker.holdem.server.deserialization.playercheck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerCheckResp {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("newlead")
    @Expose
    private String newlead;

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
}
