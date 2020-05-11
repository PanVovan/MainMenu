package com.poker.holdem.server.deserialization.playerraise;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.poker.holdem.server.deserialization.playercheck.PlayerCheckResp;

import java.lang.reflect.Type;

public class DesPlayerRaiseResp implements JsonDeserializer<PlayerRaiseResp> {
    @Override
    public PlayerRaiseResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        PlayerRaiseResp playerRaiseResp = new PlayerRaiseResp();
        playerRaiseResp.setName(jsonObject.get("name").getAsString());
        playerRaiseResp.setNewlead(jsonObject.get("newlead").getAsString());
        playerRaiseResp.setRate(jsonObject.get("rate").getAsInt());
        return playerRaiseResp;
    }
}
