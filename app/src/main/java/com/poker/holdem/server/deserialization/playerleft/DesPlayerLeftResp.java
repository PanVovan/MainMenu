package com.poker.holdem.server.deserialization.playerleft;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesPlayerLeftResp implements JsonDeserializer<PlayerLeftResp> {
    @Override
    public PlayerLeftResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        PlayerLeftResp playerLeftResp = new PlayerLeftResp();
        playerLeftResp.setName(jsonObject.get("name").getAsString());
        playerLeftResp.setNewlead(jsonObject.get("newlead").getAsString());
        playerLeftResp.setNewround(jsonObject.get("newround").getAsBoolean());
        return playerLeftResp;
    }
}
