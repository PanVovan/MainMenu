package com.poker.holdem.server.deserialization.playercheck;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesPlayerCheckResp implements JsonDeserializer<PlayerCheckResp> {
    @Override
    public PlayerCheckResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        PlayerCheckResp playerCheckResp = new PlayerCheckResp();
        playerCheckResp.setName(jsonObject.get("name").getAsString());
        playerCheckResp.setNewlead(jsonObject.get("newlead").getAsString());
        playerCheckResp.setNewround(jsonObject.get("newround").getAsBoolean());
        return playerCheckResp;
    }
}
