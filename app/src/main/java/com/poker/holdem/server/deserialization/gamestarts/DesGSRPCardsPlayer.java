package com.poker.holdem.server.deserialization.gamestarts;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DesGSRPCardsPlayer implements JsonDeserializer<GameStartsRPCardsPlayer> {
    @Override
    public GameStartsRPCardsPlayer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = new JsonObject();
        GameStartsRPCardsPlayer gameStartsRPCardsPlayer = new GameStartsRPCardsPlayer();

        gameStartsRPCardsPlayer.setPlayername(jsonObject.get("playername").getAsString());

        ArrayList<Integer> cards = new ArrayList<>();
        for (JsonElement i: jsonObject.get("cards").getAsJsonArray())
            cards.add(i.getAsInt());
        gameStartsRPCardsPlayer.setCards(cards);

        return gameStartsRPCardsPlayer;
    }
}
