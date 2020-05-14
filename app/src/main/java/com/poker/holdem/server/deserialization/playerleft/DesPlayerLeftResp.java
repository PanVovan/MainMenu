package com.poker.holdem.server.deserialization.playerleft;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.logging.Logger;

public class DesPlayerLeftResp implements JsonDeserializer<PlayerLeftResp> {
    @Override
    public PlayerLeftResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        PlayerLeftResp playerLeftResp = new PlayerLeftResp();
        playerLeftResp.setName(jsonObject.get("name").getAsString());
        try {
            playerLeftResp.setNewlead(jsonObject.get("newlead").getAsString());
        }catch (Exception e){
            //если мы были последним игроком в комнате, то, конечно же,
            //больше ведущего игрока там не будет от слова соысем
            //в этом случае мы не будем использовать это поле,
            //и просто инициализируем его пустой строкой
            playerLeftResp.setNewlead("");
            Logger.getAnonymousLogger().info("<-If we leave lobby it's ok. If not, check why \"playerleft\" \"newlead\" is null.");
        }

        playerLeftResp.setNewround(jsonObject.get("newround").getAsBoolean());
        return playerLeftResp;
    }
}
