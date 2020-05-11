package com.poker.holdem.server.deserialization.enterlobby;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DesPlayerCards implements JsonDeserializer<LobbyInfoPlayerCardsResp> {
    @Override
    public LobbyInfoPlayerCardsResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        LobbyInfoPlayerCardsResp lobbyInfoPlayerCardsResp = new LobbyInfoPlayerCardsResp();


        lobbyInfoPlayerCardsResp.setPlayername(jsonObject.get("playername").getAsString());

        JsonArray cards = jsonObject.get("cards").getAsJsonArray();
        ArrayList<Integer> cardsArray = new ArrayList<>();
        for (JsonElement i: cards)
            cardsArray.add(i.getAsInt());
        lobbyInfoPlayerCardsResp.setCards(cardsArray);

        return lobbyInfoPlayerCardsResp;
    }
}

