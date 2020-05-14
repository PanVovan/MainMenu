package com.poker.holdem.server.deserialization.youcheck;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesYouCheckResp implements JsonDeserializer<YouCheckResp> {
    @Override
    public YouCheckResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        YouCheckResp youCheckResp = new YouCheckResp();
        youCheckResp.setFlag(jsonObject.get("flag").getAsBoolean());
        youCheckResp.setNewlead(jsonObject.get("newlead").getAsString());
        youCheckResp.setNewround(jsonObject.get("newround").getAsBoolean());
        return youCheckResp;
    }
}
