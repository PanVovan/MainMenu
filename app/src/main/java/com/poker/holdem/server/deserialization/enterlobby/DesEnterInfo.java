package com.poker.holdem.server.deserialization.enterlobby;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DesEnterInfo implements JsonDeserializer<LobbyInfoResp> {
    @Override
    public LobbyInfoResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        LobbyInfoResp lobbyInfoResp = new LobbyInfoResp();

        lobbyInfoResp.setBank(jsonObject.get("bank").getAsInt());
        lobbyInfoResp.setRate(jsonObject.get("rate").getAsInt());
        lobbyInfoResp.setName(jsonObject.get("name").getAsString());
        lobbyInfoResp.setIsgamerunning(jsonObject.get("isgamerunning").getAsBoolean());
        lobbyInfoResp.setRounds_done(jsonObject.get("rounds_done").getAsInt());

        LobbyCardsResp cards = context.deserialize(jsonObject.get("cards").getAsJsonObject(), LobbyCardsResp.class);
        lobbyInfoResp.setCards(cards);

        ArrayList<LobbyPlayerResp> playersInGame = new ArrayList<>();
        for (JsonElement i: jsonObject.get("playersingame").getAsJsonArray())
            playersInGame.add(context.deserialize(i, LobbyPlayerResp.class));
        lobbyInfoResp.setPlayersingame(playersInGame);

        ArrayList<LobbyPlayerResp> allPlayers = new ArrayList<>();
        for (JsonElement i: jsonObject.get("allplayers").getAsJsonArray())
            allPlayers.add(context.deserialize(i, LobbyPlayerResp.class));
        lobbyInfoResp.setAllplayers(allPlayers);

        return lobbyInfoResp;
    }
}
