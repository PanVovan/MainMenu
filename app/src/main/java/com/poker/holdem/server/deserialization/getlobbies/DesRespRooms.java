package com.poker.holdem.server.deserialization.getlobbies;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesRespRooms implements JsonDeserializer<RespRooms> {
    @Override
    public RespRooms deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        RespRooms respRooms = new RespRooms();
        JsonArray jsonArray = jsonObject.getAsJsonArray("rooms");

        for(JsonElement entry: jsonArray){
            RespRoom respRoom = context.deserialize(entry, RespRoom.class);
            respRooms.addRoom(respRoom);
        }

        return respRooms;
    }
}
