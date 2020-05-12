package com.poker.holdem.server.deserialization.restore;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesRestoreResp implements JsonDeserializer<RestoreResp> {
    @Override
    public RestoreResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        RestoreResp restoreResp = new RestoreResp();
        restoreResp.setDidrestore(jsonObject.get("didrestore").getAsBoolean());
        if(restoreResp.getDidrestore())
            restoreResp.setToken(jsonObject.get("token").getAsString());
            restoreResp.setRoomparams(
                    context.deserialize(jsonObject.get("roomparams").getAsJsonObject(), RestoreRoomParams.class)
            );
        return restoreResp;
    }
}
