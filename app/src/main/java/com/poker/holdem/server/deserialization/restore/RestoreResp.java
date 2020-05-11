package com.poker.holdem.server.deserialization.restore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestoreResp {
    @SerializedName("didrestore")
    @Expose
    private boolean didrestore;
    @SerializedName("roomparams")
    @Expose
    private RestoreRoomParams roomparams = null;

    public void setDidrestore (boolean didrestore){
        this.didrestore = didrestore;
    }
    public void setRoomparams(RestoreRoomParams roomparams){
        this.roomparams = roomparams;
    }

    public boolean getDidrestore (){
        return this.didrestore;
    }
    public RestoreRoomParams getRoomparams(){
        return this.roomparams;
    }
}
