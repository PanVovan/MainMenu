package com.poker.holdem.server.deserialization.youcheck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YouCheckResp {

    @SerializedName("flag")
    @Expose
    private Boolean flag;
    @SerializedName("newlead")
    @Expose
    private String newlead;

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
