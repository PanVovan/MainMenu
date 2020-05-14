package com.poker.holdem.server.deserialization.enterlobby;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.logging.Logger;

public class DesPlayer implements JsonDeserializer<LobbyPlayerResp> {
    @Override
    public LobbyPlayerResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        LobbyPlayerResp playerResp = new LobbyPlayerResp();

        try {
            playerResp.setMoney(jsonObject.get("money").getAsInt());
        }catch (Exception e){
            playerResp.setMoney(0);
            Logger.getAnonymousLogger().info(" <--- Player's money on server is not correct. That can mean that you've joined a full lobby (in this situation server can't correctly procecc your data).");
            e.printStackTrace();
        }
        playerResp.setPicture(jsonObject.get("picture").getAsInt());
        playerResp.setPlayername(jsonObject.get("name").getAsString());

        return playerResp;
    }
}
