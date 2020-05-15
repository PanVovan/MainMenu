package com.poker.holdem.server.deserialization.enterlobby;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.logging.Logger;

public class DesEnterLobbyResp implements JsonDeserializer<EnterResp> {
    @Override
    public EnterResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        EnterResp enterResp = new EnterResp();
        Logger.getAnonymousLogger().info("<-----"+jsonObject.toString());

        enterResp.setDidenter(jsonObject.get("didenter").getAsBoolean());
        if(enterResp.getDidenter()){
            LobbyInfoResp lobbyInfoResp = context.deserialize(jsonObject.get("lobbyinfo").getAsJsonObject(), LobbyInfoResp.class);
            enterResp.setLobbyinfo(lobbyInfoResp);
        }

        return enterResp;
    }
}
