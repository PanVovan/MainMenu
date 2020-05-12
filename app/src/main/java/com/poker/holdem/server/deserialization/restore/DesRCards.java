package com.poker.holdem.server.deserialization.restore;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRPCards;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRPCardsPlayer;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DesRCards implements JsonDeserializer<RestoreRPCards> {
    @Override
    public RestoreRPCards deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        RestoreRPCards restoreRPCards = new RestoreRPCards();

        ArrayList<Integer> deck = new ArrayList<>();
        for(JsonElement i: jsonObject.get("deck").getAsJsonArray())
            deck.add(i.getAsInt());
        restoreRPCards.setDeck(deck);

        ArrayList<RestoreRPCardsPlayer> players = new ArrayList<>();
        for(JsonElement i: jsonObject.get("players").getAsJsonArray())
            players.add(context.deserialize(i.getAsJsonObject(), RestoreRPCardsPlayer.class));
        restoreRPCards.setPlayers(players);

        return restoreRPCards;
    }
}
