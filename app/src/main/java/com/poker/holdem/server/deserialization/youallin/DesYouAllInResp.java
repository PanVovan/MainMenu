package com.poker.holdem.server.deserialization.youallin;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesYouAllInResp implements JsonDeserializer<YouAllInResp> {
    @Override
    public YouAllInResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        YouAllInResp youAllInResp = new YouAllInResp();
        youAllInResp.setFlag(jsonObject.get("flag").getAsBoolean());
        youAllInResp.setNewlead(jsonObject.get("newlead").getAsString());
        youAllInResp.setNewround(jsonObject.get("newround").getAsBoolean());
        return youAllInResp;
    }
}
