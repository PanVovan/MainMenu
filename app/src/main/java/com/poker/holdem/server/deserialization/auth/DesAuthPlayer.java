package com.poker.holdem.server.deserialization.auth;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesAuthPlayer implements JsonDeserializer<AuthPlayer> {
    @Override
    public AuthPlayer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        AuthPlayer authPlayer = new AuthPlayer();

        authPlayer.setId(jsonObject.get("id").getAsInt());
        authPlayer.setMoney(jsonObject.get("money").getAsInt());
        authPlayer.setName(jsonObject.get("name").getAsString());
        authPlayer.setPassword(jsonObject.get("password").getAsString());
        authPlayer.setPicture(jsonObject.get("picture").getAsInt());

        return authPlayer;
    }
}
