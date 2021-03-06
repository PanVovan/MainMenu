package com.poker.holdem.server.deserialization.youfold;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesYouFoldResp implements JsonDeserializer<YouFoldResp> {
    @Override
    public YouFoldResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        YouFoldResp youFoldResp = new YouFoldResp();
        youFoldResp.setFlag(jsonObject.get("flag").getAsBoolean());
        youFoldResp.setNewlead(jsonObject.get("newlead").getAsString());
        youFoldResp.setNewround(jsonObject.get("newround").getAsBoolean());
        return youFoldResp;
    }
}
