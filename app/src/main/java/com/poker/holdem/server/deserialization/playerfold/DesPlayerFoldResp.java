package com.poker.holdem.server.deserialization.playerfold;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesPlayerFoldResp implements JsonDeserializer<PlayerFoldResp> {
    @Override
    public PlayerFoldResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        PlayerFoldResp playerFoldResp = new PlayerFoldResp();
        playerFoldResp.setName(jsonObject.get("name").getAsString());
        playerFoldResp.setNewlead(jsonObject.get("newlead").getAsString());
        return playerFoldResp;
    }
}
