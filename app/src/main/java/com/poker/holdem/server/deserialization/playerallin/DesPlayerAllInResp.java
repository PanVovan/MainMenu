package com.poker.holdem.server.deserialization.playerallin;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesPlayerAllInResp implements JsonDeserializer<PlayerAllInResp> {

    @Override
    public PlayerAllInResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        PlayerAllInResp playerAllInResp = new PlayerAllInResp();
        playerAllInResp.setName(jsonObject.get("name").getAsString());
        playerAllInResp.setNewlead(jsonObject.get("newlead").getAsString());
        return playerAllInResp;
    }
}
