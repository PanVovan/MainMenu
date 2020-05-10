package com.poker.holdem.lobby;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllRooms {

    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
