package com.poker.holdem.server.deserialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.poker.holdem.server.deserialization.auth.AuthPlayer;
import com.poker.holdem.server.deserialization.auth.AuthResponce;
import com.poker.holdem.server.deserialization.auth.DesAuthPlayer;
import com.poker.holdem.server.deserialization.auth.DesAuthResponce;
import com.poker.holdem.server.deserialization.endgame.DesEndGameResp;
import com.poker.holdem.server.deserialization.endgame.EndgameResp;
import com.poker.holdem.server.deserialization.enterlobby.DesCards;
import com.poker.holdem.server.deserialization.enterlobby.DesEnterInfo;
import com.poker.holdem.server.deserialization.enterlobby.DesEnterLobbyResp;
import com.poker.holdem.server.deserialization.enterlobby.DesPlayer;
import com.poker.holdem.server.deserialization.enterlobby.DesPlayerCards;
import com.poker.holdem.server.deserialization.enterlobby.EnterResp;
import com.poker.holdem.server.deserialization.enterlobby.LobbyCardsResp;
import com.poker.holdem.server.deserialization.enterlobby.LobbyInfoPlayerCardsResp;
import com.poker.holdem.server.deserialization.enterlobby.LobbyInfoResp;
import com.poker.holdem.server.deserialization.enterlobby.LobbyPlayerResp;
import com.poker.holdem.server.deserialization.gamestarts.DesGSCards;
import com.poker.holdem.server.deserialization.gamestarts.DesGSRPCardsPlayer;
import com.poker.holdem.server.deserialization.gamestarts.DesGSRPPlayer;
import com.poker.holdem.server.deserialization.gamestarts.DesGSResp;
import com.poker.holdem.server.deserialization.gamestarts.DesGSRoomParams;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRPCards;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRPCardsPlayer;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRPPlayer;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsResp;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsRoomParams;
import com.poker.holdem.server.deserialization.getlobbies.DesRespRoom;
import com.poker.holdem.server.deserialization.getlobbies.DesRespRooms;
import com.poker.holdem.server.deserialization.getlobbies.RespRoom;
import com.poker.holdem.server.deserialization.getlobbies.RespRooms;
import com.poker.holdem.server.deserialization.playerallin.PlayerAllInResp;
import com.poker.holdem.server.deserialization.playercheck.DesPlayerCheckResp;
import com.poker.holdem.server.deserialization.playercheck.PlayerCheckResp;
import com.poker.holdem.server.deserialization.playerfold.PlayerFoldResp;
import com.poker.holdem.server.deserialization.playerleft.PlayerLeftResp;
import com.poker.holdem.server.deserialization.registration.DesRegResp;
import com.poker.holdem.server.deserialization.registration.RegResp;
import com.poker.holdem.server.deserialization.restore.DesRCards;
import com.poker.holdem.server.deserialization.restore.DesRRPCardsPlayer;
import com.poker.holdem.server.deserialization.restore.DesRRPPlayer;
import com.poker.holdem.server.deserialization.restore.DesRRoomParams;
import com.poker.holdem.server.deserialization.restore.DesRestoreResp;
import com.poker.holdem.server.deserialization.restore.RestoreRPCards;
import com.poker.holdem.server.deserialization.restore.RestoreRPCardsPlayer;
import com.poker.holdem.server.deserialization.restore.RestoreRPPlayer;
import com.poker.holdem.server.deserialization.restore.RestoreResp;
import com.poker.holdem.server.deserialization.restore.RestoreRoomParams;
import com.poker.holdem.server.deserialization.youallin.YouAllInResp;
import com.poker.holdem.server.deserialization.youcheck.DesYouCheckResp;
import com.poker.holdem.server.deserialization.youcheck.YouCheckResp;
import com.poker.holdem.server.deserialization.youfold.YouFoldResp;
import com.poker.holdem.server.deserialization.youraise.YouRaiseResp;

import java.lang.reflect.Type;

public class MyDeserializer {
    //эту десериализацию я сделал первой, так что комментирую только её
    public static AuthResponce desAuthResponce(String resp){
        //нам приходит строка (незашифрованная),
        //в которой нахожится некий json объект, например:
        //      {
        //          "flag": true
        //          ,"token": "HFQ5Y"
        //          ,"item":
        //                  {
        //                      "id": 1,
        //                          "name": "Kirill1",
        //                          "password": "admin",
        //                          "picture": 1,
        //                          "money": 60
        //                  }
		//      	,"newauthtoken": "NEW_SOME_TOKEN"
        //      }
        //  Здесь есть две сущности, которые надо превратить в классы-
        //  item и основной элемент (item - данные авторизованного игрока
        //  Объекту Gson gson нужно знать, что делать с обоими объектами.
        //  Поэтомк передаём их билдеру и создаём десериализатор.
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AuthResponce.class, new DesAuthResponce())
                .registerTypeAdapter(AuthPlayer.class, new DesAuthPlayer())
                .create();
        //опять же, передаётся строка с объектом и контейнер(класс),
        //куда её надо поместить
        return gson.fromJson(resp, AuthResponce.class);
    }


    public static RespRooms desGetLobbiesResponce(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RespRoom.class, new DesRespRoom())
                .registerTypeAdapter(RespRooms.class, new DesRespRooms())
                .create();
        return gson.fromJson(resp, RespRooms.class);
    }


    public static RegResp desRegistrationResponce(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RegResp.class, new DesRegResp())
                .create();
        return gson.fromJson(resp, RegResp.class);
    }


    public static EnterResp desEnterLobbyResponce(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(EnterResp.class, new DesEnterLobbyResp())
                .registerTypeAdapter(LobbyCardsResp.class, new DesCards())
                .registerTypeAdapter(LobbyInfoResp.class, new DesEnterInfo())
                .registerTypeAdapter(LobbyInfoPlayerCardsResp.class, new DesPlayerCards())
                .registerTypeAdapter(LobbyPlayerResp.class, new DesPlayer())
                .create();
        return gson.fromJson(resp, EnterResp.class);
    }


    public static GameStartsResp desGameStartsResponce(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(GameStartsResp.class, new DesGSResp())
                .registerTypeAdapter(GameStartsRoomParams.class, new DesGSRoomParams())
                .registerTypeAdapter(GameStartsRPCards.class, new DesGSCards())
                .registerTypeAdapter(GameStartsRPCardsPlayer.class, new DesGSRPCardsPlayer())
                .registerTypeAdapter(GameStartsRPPlayer.class, new DesGSRPPlayer())
                .create();
        return gson.fromJson(resp, GameStartsResp.class);
    }

    public static RestoreResp desRestoreResp(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RestoreResp.class, new DesRestoreResp())
                .registerTypeAdapter(RestoreRoomParams.class, new DesRRoomParams())
                .registerTypeAdapter(RestoreRPCards.class, new DesRCards())
                .registerTypeAdapter(RestoreRPCardsPlayer.class, new DesRRPCardsPlayer())
                .registerTypeAdapter(RestoreRPPlayer.class, new DesRRPPlayer())
                .create();
        return gson.fromJson(resp, RestoreResp.class);
    }


    public static EndgameResp desEndgameResp(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(EndgameResp.class, new DesEndGameResp())
                .create();
        return gson.fromJson(resp, EndgameResp.class);
    }


    public static YouCheckResp desYouCheckResp(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(YouCheckResp.class, new DesYouCheckResp())
                .create();
        return gson.fromJson(resp, YouCheckResp.class);
    }


    public static YouRaiseResp desYouRaiseResp(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(YouRaiseResp.class, new DesPlayerCheckResp())
                .create();
        return gson.fromJson(resp, YouRaiseResp.class);
    }


    public static YouAllInResp desYouAllInResp(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(YouCheckResp.class, new DesYouCheckResp())
                .create();
        return gson.fromJson(resp, YouAllInResp.class);
    }


    public static YouFoldResp desYouFoldResp(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(YouCheckResp.class, new DesYouCheckResp())
                .create();
        return gson.fromJson(resp, YouFoldResp.class);
    }


    public static Integer desYouLeft(String resp){
        //типа когда выходишь, тебе на всякий случай присылают твои деньги
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Integer.class, (JsonDeserializer<Integer>) (json, typeOfT, context) -> {
                    JsonObject jsonObject = json.getAsJsonObject();
                    return jsonObject.get("money").getAsInt();
                })
                .create();
        return gson.fromJson(resp, Integer.class);
    }


    public static PlayerCheckResp desPlayerCheckResp(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PlayerCheckResp.class, new DesPlayerCheckResp())
                .create();
        return gson.fromJson(resp, PlayerCheckResp.class);
    }


    public static PlayerAllInResp desPlayerAllInResp(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PlayerCheckResp.class, new DesPlayerCheckResp())
                .create();
        return gson.fromJson(resp, PlayerAllInResp.class);
    }


    public static PlayerFoldResp desPlayerFoldResp(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PlayerCheckResp.class, new DesPlayerCheckResp())
                .create();
        return gson.fromJson(resp, PlayerFoldResp.class);
    }


    public static PlayerLeftResp desPlayerLeftResp(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(PlayerCheckResp.class, new DesPlayerCheckResp())
                .create();
        return gson.fromJson(resp, PlayerLeftResp.class);
    }


    //тут я решил не делать отдельный пакет ради одного параметра
    public static String desPlayerStopsRespName(String resp){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(String.class, (JsonDeserializer<String>) (json, typeOfT, context) -> {
                    JsonObject jsonObject = json.getAsJsonObject();
                    return jsonObject.get("name").getAsString();
                })
                .create();
        return gson.fromJson(resp, String.class);
    }


    //зачем по многу раз переписывать?
    public static String desPlayerRestoresRespName(String resp){
       return desPlayerStopsRespName(resp);
    }

}
