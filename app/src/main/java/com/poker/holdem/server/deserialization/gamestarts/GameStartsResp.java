package com.poker.holdem.server.deserialization.gamestarts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameStartsResp {
    @SerializedName("roomparams")
    @Expose
    private GameStartsRoomParams roomparams;
    @SerializedName("lead")
    @Expose
    private String lead;

    public GameStartsRoomParams getRoomparams() {
        return roomparams;
    }

    public void setRoomparams(GameStartsRoomParams roomparams) {
        this.roomparams = roomparams;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }
}
