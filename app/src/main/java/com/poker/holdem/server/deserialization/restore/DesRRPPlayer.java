package com.poker.holdem.server.deserialization.restore;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRPPlayer;

import java.lang.reflect.Type;

public class DesRRPPlayer implements JsonDeserializer<RestoreRPPlayer> {
    @Override
    public RestoreRPPlayer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        RestoreRPPlayer restoreRPPlayer = new RestoreRPPlayer();

        restoreRPPlayer.setMoney(jsonObject.get("money").getAsInt());
        restoreRPPlayer.setName(jsonObject.get("name").getAsString());
        restoreRPPlayer.setPicture(jsonObject.get("picture").getAsInt());


        return restoreRPPlayer;
    }
}
