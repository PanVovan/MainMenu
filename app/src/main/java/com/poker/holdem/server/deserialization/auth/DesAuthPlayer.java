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

        //важный момент для понимания:
        //десериализация не происходит мнгновенно
        //мы не можем сразу сделать из json объект класса
        //необходимо сначала создать класс, а потом,
        //считывая json, инициализировать поля класса
        AuthPlayer authPlayer = new AuthPlayer();

        //тупо берём поля, и присваиваем их классу
        authPlayer.setId(jsonObject.get("id").getAsInt());
        authPlayer.setMoney(jsonObject.get("money").getAsInt());
        authPlayer.setName(jsonObject.get("name").getAsString());
        authPlayer.setPassword(jsonObject.get("password").getAsString());
        authPlayer.setPicture(jsonObject.get("picture").getAsInt());

        return authPlayer;
    }
}
