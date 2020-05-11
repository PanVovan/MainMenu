package com.poker.holdem.server.deserialization.auth;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DesAuthResponce implements JsonDeserializer<AuthResponce> {
    @Override
    public AuthResponce deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        AuthResponce authResponce = new AuthResponce();

        authResponce.setFlag(jsonObject.get("flag").getAsBoolean());
        //Если мы авторизацию не прошли, то флаг будет false,
        //а поля item, newauthtoken и token будут пустыми. Если мы попытаемся десериализовать
        //пустое поле, то возникнет ошибка, поэтому мы смотрим на флаг
        //перед десериализацией item, newauthtoken и token
        if(authResponce.getFlag()){
            authResponce.setNewauthtoken(jsonObject.get("newauthtoken").getAsString());
            authResponce.setToken(jsonObject.get("token").getAsString());
            //Тут свершается магия - мы передаём объекту десериализвтора Gson gson
            //item, которую он уже умеет понимать(см MyDeserializer GsonBuilder)
            //и класс, который должен получиться.
            AuthPlayer item = context.deserialize(jsonObject.get("item").getAsJsonObject(), AuthPlayer.class);
            authResponce.setAuthPlayer(item);
        }

        return authResponce;
    }
}
