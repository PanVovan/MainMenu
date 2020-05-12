package com.poker.holdem.server.deserialization.restore;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRPCardsPlayer;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DesRRPCardsPlayer implements JsonDeserializer<RestoreRPCardsPlayer> {
    @Override
    public RestoreRPCardsPlayer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = new JsonObject();
        RestoreRPCardsPlayer restoreRPCardsPlayer = new RestoreRPCardsPlayer();

        restoreRPCardsPlayer.setPlayername(jsonObject.get("playername").getAsString());

        ArrayList<Integer> cards = new ArrayList<>();
        for (JsonElement i: jsonObject.get("cards").getAsJsonArray())
            cards.add(i.getAsInt());
        restoreRPCardsPlayer.setCards(cards);

        return restoreRPCardsPlayer;
    }
}
