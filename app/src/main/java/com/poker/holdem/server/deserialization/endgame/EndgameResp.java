package com.poker.holdem.server.deserialization.endgame;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EndgameResp {
    @SerializedName("win_val")
    @Expose
    private Integer winVal;
    @SerializedName("winners")
    @Expose
    private List<String> winners = null;

    public void addWinner(String winner){
        this.winners.add(winner);
    }

    public Integer getWinVal() {
        return winVal;
    }

    public void setWinVal(Integer winVal) {
        this.winVal = winVal;
    }

    public List<String> getWinners() {
        return winners;
    }

    public void setWinners(List<String> winners) {
        this.winners = winners;
    }
}
