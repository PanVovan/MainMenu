package com.poker.holdem.server.deserialization.endgame;


import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.logging.Logger;

public class DesEndGameResp implements JsonDeserializer<EndgameResp> {

    @Override
    public EndgameResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        EndgameResp endgameResp = new EndgameResp();

        if(jsonObject.get("win_val").getAsString() == null)
            endgameResp.setWinVal(0);
        else
            endgameResp.setWinVal(jsonObject.get("win_val").getAsInt());

        JsonArray winners = jsonObject.get("winners").getAsJsonArray();
        for(JsonElement i: winners) {
            endgameResp.addWinner(i.getAsString());
        }

        return endgameResp;
    }
}
