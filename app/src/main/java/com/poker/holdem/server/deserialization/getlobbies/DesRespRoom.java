package com.poker.holdem.server.deserialization.getlobbies;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesRespRoom implements JsonDeserializer<RespRoom> {
    @Override
    public RespRoom deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        RespRoom respRoom = new RespRoom();

        respRoom.setLength(jsonObject.get("length").getAsInt());
        respRoom.setRate(jsonObject.get("rate").getAsInt());
        respRoom.setName(jsonObject.get("name").getAsString());

        return respRoom;
    }
}
