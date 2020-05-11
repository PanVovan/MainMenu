package com.poker.holdem.server.deserialization.newplayerjoin;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesNewPlayerJoinResp implements JsonDeserializer<NewPlayerJoinResp> {
    @Override
    public NewPlayerJoinResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        NewPlayerJoinResp newPlayerJoinResp = new NewPlayerJoinResp();
        newPlayerJoinResp.setId(jsonObject.get("id").getAsInt());
        newPlayerJoinResp.setName(jsonObject.get("name").getAsString());
        newPlayerJoinResp.setMoney(jsonObject.get("money").getAsInt());
        newPlayerJoinResp.setPicture(jsonObject.get("picture").getAsInt());
        return newPlayerJoinResp;
    }
}
