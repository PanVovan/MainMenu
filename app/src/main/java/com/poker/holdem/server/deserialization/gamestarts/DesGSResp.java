package com.poker.holdem.server.deserialization.gamestarts;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesGSResp implements JsonDeserializer<GameStartsResp> {
    @Override
    public GameStartsResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        GameStartsResp gameStartsResp = new GameStartsResp();

        gameStartsResp.setLead(jsonObject.get("lead").getAsString());
        gameStartsResp.setRoomparams(
                context.deserialize
                        (jsonObject.get("roomparams").getAsJsonObject()
                                ,GameStartsRoomParams.class));

        return gameStartsResp;
    }
}
