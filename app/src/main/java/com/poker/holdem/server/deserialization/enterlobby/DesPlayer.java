package com.poker.holdem.server.deserialization.enterlobby;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesPlayer implements JsonDeserializer<LobbyPlayerResp> {
    @Override
    public LobbyPlayerResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        LobbyPlayerResp playerResp = new LobbyPlayerResp();

        playerResp.setMoney(jsonObject.get("money").getAsInt());
        playerResp.setPicture(jsonObject.get("picture").getAsInt());
        playerResp.setPlayername(jsonObject.get("playername").getAsString());

        return playerResp;
    }
}
