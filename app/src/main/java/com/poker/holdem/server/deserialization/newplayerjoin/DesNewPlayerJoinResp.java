package com.poker.holdem.server.deserialization.newplayerjoin;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.logging.Logger;

public class DesNewPlayerJoinResp implements JsonDeserializer<NewPlayerJoinResp> {
    @Override
    public NewPlayerJoinResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        NewPlayerJoinResp newPlayerJoinResp = new NewPlayerJoinResp();
        newPlayerJoinResp.setId(jsonObject.get("id").getAsInt());
        newPlayerJoinResp.setName(jsonObject.get("name").getAsString());
        try {
            newPlayerJoinResp.setMoney(jsonObject.get("money").getAsInt());
        }catch (Exception e){
            newPlayerJoinResp.setMoney(0);
            Logger.getAnonymousLogger().info(" <--- Player's money on server is not correct. That can mean that you've joined a full lobby (in this situation server can't correctly procecc your data).");
            e.printStackTrace();
        }
        newPlayerJoinResp.setPicture(jsonObject.get("picture").getAsInt());
        return newPlayerJoinResp;
    }
}
