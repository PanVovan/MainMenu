package com.poker.holdem.server.deserialization.gamestarts;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DesGSRoomParams implements JsonDeserializer<GameStartsRoomParams> {
    @Override
    public GameStartsRoomParams deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        GameStartsRoomParams gameStartsRoomParams = new GameStartsRoomParams();

        gameStartsRoomParams.setBank(jsonObject.get("bank").getAsInt());
        gameStartsRoomParams.setRate(jsonObject.get("rate").getAsInt());
        gameStartsRoomParams.setRounds_done(jsonObject.get("rounds_done").getAsInt());
        //Конечно, с началом игры этот флаг будет true
        //но пока пусть будет
        gameStartsRoomParams.setIsgamerunning(jsonObject.get("isgamerunning").getAsBoolean());
        gameStartsRoomParams.setName(jsonObject.get("name").getAsString());

        GameStartsRPCards cards = context.deserialize(jsonObject.get("cards").getAsJsonObject(), GameStartsRPCards.class);
        gameStartsRoomParams.setCards(cards);
        ArrayList<GameStartsRPPlayer> gamePlayers = new ArrayList<>();
        for (JsonElement i: jsonObject.get("playersingame").getAsJsonArray())
            gamePlayers.add(context.deserialize(i.getAsJsonObject(), GameStartsRPPlayer.class));
        gameStartsRoomParams.setPlayersingame(gamePlayers);

        ArrayList<GameStartsRPPlayer> allPlayers = new ArrayList<>();
        for (JsonElement i: jsonObject.get("allplayers").getAsJsonArray())
            allPlayers.add(context.deserialize(i.getAsJsonObject(), GameStartsRPPlayer.class));
        gameStartsRoomParams.setPlayersingame(allPlayers);


        return gameStartsRoomParams;
    }
}
