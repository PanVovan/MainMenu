package com.poker.holdem.server.deserialization.enterlobby;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DesCards implements JsonDeserializer<LobbyCardsResp> {
    @Override
    public LobbyCardsResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        LobbyCardsResp lobbyCardsResp = new LobbyCardsResp();

        JsonArray deck = jsonObject.get("deck").getAsJsonArray();
        ArrayList<Integer> deckArray = new ArrayList<>();
        for (JsonElement i: deck)
            deckArray.add(i.getAsInt());
        lobbyCardsResp.setDeck(deckArray);

        JsonArray players = jsonObject.get("players").getAsJsonArray();
        ArrayList<LobbyInfoPlayerCardsResp> playersArray = new ArrayList<>();
        for (JsonElement i: players)
            playersArray.add(context.deserialize(i, LobbyPlayerResp.class));
        lobbyCardsResp.setPlayers(playersArray);


        return lobbyCardsResp;
    }
}
