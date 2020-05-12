package com.poker.holdem.server.deserialization.registration;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.poker.holdem.server.deserialization.getlobbies.RespRoom;

import java.lang.reflect.Type;

public class DesRegResp implements JsonDeserializer<RegResp> {
    @Override
    public RegResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        RegResp respRoom = new RegResp();
        respRoom.setIsReg(jsonObject.get("is_reg").getAsBoolean());
        return respRoom;
    }
}
