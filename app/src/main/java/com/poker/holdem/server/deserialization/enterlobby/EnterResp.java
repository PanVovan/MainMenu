package com.poker.holdem.server.deserialization.enterlobby;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnterResp {
    @SerializedName("didenter")
    @Expose
    private Boolean didenter;
    @SerializedName("lobbyinfo")
    @Expose
    private LobbyInfoResp lobbyinfo;

    public Boolean getDidenter() {
        return didenter;
    }

    public void setDidenter(Boolean didenter) {
        this.didenter = didenter;
    }

    public LobbyInfoResp getLobbyinfo() {
        return lobbyinfo;
    }

    public void setLobbyinfo(LobbyInfoResp lobbyinfo) {
        this.lobbyinfo = lobbyinfo;
    }
}
