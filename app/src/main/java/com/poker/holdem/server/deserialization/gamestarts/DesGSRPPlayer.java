package com.poker.holdem.server.deserialization.gamestarts;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesGSRPPlayer implements JsonDeserializer<GameStartsRPPlayer> {
    @Override
    public GameStartsRPPlayer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        GameStartsRPPlayer gameStartsRPPlayer = new GameStartsRPPlayer();

        gameStartsRPPlayer.setMoney(jsonObject.get("money").getAsInt());
        gameStartsRPPlayer.setName(jsonObject.get("name").getAsString());
        gameStartsRPPlayer.setPicture(jsonObject.get("picture").getAsInt());

        return gameStartsRPPlayer;
    }
}
