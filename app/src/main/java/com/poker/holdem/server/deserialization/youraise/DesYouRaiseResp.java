package com.poker.holdem.server.deserialization.youraise;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.poker.holdem.server.deserialization.youcheck.YouCheckResp;

import java.lang.reflect.Type;

public class DesYouRaiseResp implements JsonDeserializer<YouRaiseResp> {
    @Override
    public YouRaiseResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        YouRaiseResp youRaiseResp = new YouRaiseResp();
        youRaiseResp.setFlag(jsonObject.get("flag").getAsBoolean());
        youRaiseResp.setNewlead(jsonObject.get("newlead").getAsString());
        return youRaiseResp;
    }
}
