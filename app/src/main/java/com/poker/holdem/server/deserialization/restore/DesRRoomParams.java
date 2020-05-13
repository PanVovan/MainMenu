package com.poker.holdem.server.deserialization.restore;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRPCards;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRPPlayer;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRoomParams;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DesRRoomParams implements JsonDeserializer<RestoreRoomParams> {
    @Override
    public RestoreRoomParams deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        RestoreRoomParams restoreRoomParams = new RestoreRoomParams();

        restoreRoomParams.setBank(jsonObject.get("bank").getAsInt());
        restoreRoomParams.setRate(jsonObject.get("rate").getAsInt());
        restoreRoomParams.setLead(jsonObject.get("lead").getAsString());
        restoreRoomParams.setRounds_done(jsonObject.get("rounds_done").getAsInt());
        //Конечно, с началом игры этот флаг будет true
        //но пока пусть будет
        restoreRoomParams.setIsgamerunning(jsonObject.get("isgamerunning").getAsBoolean());
        restoreRoomParams.setName(jsonObject.get("name").getAsString());

        RestoreRPCards cards = context.deserialize(jsonObject.get("cards").getAsJsonObject(), GameStartsRPCards.class);
        restoreRoomParams.setCards(cards);

        ArrayList<RestoreRPPlayer> gamePlayers = new ArrayList<>();
        for (JsonElement i: jsonObject.get("playersingame").getAsJsonArray())
            gamePlayers.add(context.deserialize(i.getAsJsonObject(), GameStartsRPPlayer.class));
        restoreRoomParams.setPlayersingame(gamePlayers);

        ArrayList<RestoreRPPlayer> allPlayers = new ArrayList<>();
        for (JsonElement i: jsonObject.get("allplayers").getAsJsonArray())
            allPlayers.add(context.deserialize(i.getAsJsonObject(), GameStartsRPPlayer.class));
        restoreRoomParams.setPlayersingame(allPlayers);


        return restoreRoomParams;
    }
}
