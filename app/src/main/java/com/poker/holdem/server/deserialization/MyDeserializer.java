package com.poker.holdem.server.deserialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Preconditions;
import com.poker.holdem.server.deserialization.auth.AuthPlayer;
import com.poker.holdem.server.deserialization.auth.AuthResponce;
import com.poker.holdem.server.deserialization.auth.DesAuthPlayer;
import com.poker.holdem.server.deserialization.auth.DesAuthResponce;
import com.poker.holdem.server.deserialization.getlobbies.DesRespRoom;
import com.poker.holdem.server.deserialization.getlobbies.DesRespRooms;
import com.poker.holdem.server.deserialization.getlobbies.RespRoom;
import com.poker.holdem.server.deserialization.getlobbies.RespRooms;

public class MyDeserializer {
    public static AuthResponce desAuthResponce(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AuthResponce.class, new DesAuthResponce())
                .registerTypeAdapter(AuthPlayer.class, new DesAuthPlayer())
                .create();
        AuthResponce result = gson.fromJson(resp, AuthResponce.class);
        return result;
    }
    public static RespRooms desGetLobbiesResponce(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RespRoom.class, new DesRespRoom())
                .registerTypeAdapter(RespRooms.class, new DesRespRooms())
                .create();
        RespRooms respRooms = gson.fromJson(resp, RespRooms.class);
        return respRooms;
    }
}
