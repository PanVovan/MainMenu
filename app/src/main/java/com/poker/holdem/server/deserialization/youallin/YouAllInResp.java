package com.poker.holdem.server.deserialization.youallin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YouAllInResp {
    @SerializedName("flag")
    @Expose
    private Boolean flag;
    @SerializedName("newlead")
    @Expose
    private String newlead;
    @SerializedName("newround")
    @Expose
    private boolean newround;

    public boolean getNewround() {
        return newround;
    }

    public void setNewround(boolean newround) {
        this.newround = newround;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getNewlead() {
        return newlead;
    }

    public void setNewlead(String newlead) {
        this.newlead = newlead;
    }
}
