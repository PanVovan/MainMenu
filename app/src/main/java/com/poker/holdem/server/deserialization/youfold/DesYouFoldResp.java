package com.poker.holdem.server.deserialization.youfold;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.poker.holdem.server.deserialization.youcheck.YouCheckResp;

import java.lang.reflect.Type;

public class DesYouFoldResp implements JsonDeserializer<YouFoldResp> {
    @Override
    public YouFoldResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        YouFoldResp youFoldResp = new YouFoldResp();
        youFoldResp.setFlag(jsonObject.get("flag").getAsBoolean());
        youFoldResp.setNewlead(jsonObject.get("newlead").getAsString());
        return youFoldResp;
    }
}
