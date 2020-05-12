package com.poker.holdem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.poker.holdem.constants.Constants;
import com.poker.holdem.logic.MyThread;
import com.poker.holdem.server.deserialization.MyDeserializer;
import com.poker.holdem.server.deserialization.endgame.EndgameResp;
import com.poker.holdem.server.deserialization.enterlobby.EnterResp;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsResp;
import com.poker.holdem.server.deserialization.newplayerjoin.NewPlayerJoinResp;
import com.poker.holdem.server.deserialization.playerallin.PlayerAllInResp;
import com.poker.holdem.server.deserialization.playercheck.PlayerCheckResp;
import com.poker.holdem.server.deserialization.playerfold.PlayerFoldResp;
import com.poker.holdem.server.deserialization.playerleft.PlayerLeftResp;
import com.poker.holdem.server.deserialization.playerraise.PlayerRaiseResp;
import com.poker.holdem.server.deserialization.restore.RestoreResp;
import com.poker.holdem.server.deserialization.youallin.YouAllInResp;
import com.poker.holdem.server.deserialization.youcheck.YouCheckResp;
import com.poker.holdem.server.deserialization.youfold.YouFoldResp;
import com.poker.holdem.server.deserialization.youraise.YouRaiseResp;
import com.poker.holdem.server.serialization.GetJSON;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ServerController implements GameContract.Server {

    private GameContract.Presenter presenter;
    private MyThread handlerThread;

    ////Скорее всего он понадобится для прослушивания, но может и нет, я не шарю как это работает
    //private Thread listenThread;

    ////На всякий, если не понадобится
    //@Override
    //public void run() {

    //}

    private Socket socket;
    private SharedPreferences prefs;

    private String SESSION_TOKEN;
    private String PLAYER_NAME;
    private String ROOM_NAME = "";

    ServerController(GameContract.Presenter presenter){

        this.SESSION_TOKEN = PokerApplicationManager
                .getInstance()
                .getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
                .getString(Constants.SESSION_TOKEN, "");
        this.PLAYER_NAME = PokerApplicationManager
                .getInstance()
                .getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
                .getString(Constants.PLAYER_NAME, "");

        this.handlerThread = new MyThread("Server Thread");
        this.presenter = presenter;

        this.socket = PokerApplicationManager.getInstance().getSocket();
        this.socket.on("enterlobby", onEnterLobby);
        this.socket.on("newplayerjoinedlobby", onNewPlayerJoin);
        this.socket.on("gamestarts", onGameStarts);
        this.socket.on("youcheck", onYouCheck);
        this.socket.on("playercheck", onPlayerCheck);
        this.socket.on("youraise", onYouRaise);
        this.socket.on("playerraise", onPlayerRaise);
        this.socket.on("youfold", onYouFold);
        this.socket.on("playerfold", onPlayerFold);
        this.socket.on("youallin", onYouAllIn);
        this.socket.on("playerallin", onPlayerAllIn);
        this.socket.on("youleft", onYouLeft);
        this.socket.on("playerleft", onPlayerLeft);
        this.socket.on("playerstops", onPlayerStops);
        this.socket.on("restore", onRestore);
        this.socket.on("playerrestores", onPlayerRestore);
        this.socket.on("endgame", onEndGame);
        this.socket.connect();
    }

    //TODO: расписать листенеры
    private Emitter.Listener onEnterLobby = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            EnterResp enterResp = MyDeserializer.desEnterLobbyResponce(args[0].toString());
            //this.presenter.acceptMessageFromServerEnterLobby(enterResp)
        }
    };
    private Emitter.Listener onGameStarts = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            GameStartsResp gameStartsResp = MyDeserializer.desGameStartsResponce(args[0].toString());

        }
    };
    private Emitter.Listener onNewPlayerJoin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            NewPlayerJoinResp newPlayerJoinResp = MyDeserializer.desNewPlayerJoinResp(args[0].toString());

        }
    };
    private Emitter.Listener onPlayerAllIn = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            PlayerAllInResp playerAllInResp = MyDeserializer.desPlayerAllInResp(args[0].toString());

        }
    };
    private Emitter.Listener onPlayerCheck = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            PlayerCheckResp playerCheckResp = MyDeserializer.desPlayerCheckResp(args[0].toString());

        }
    };
    private Emitter.Listener onPlayerFold = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            PlayerFoldResp playerFoldResp = MyDeserializer.desPlayerFoldResp(args[0].toString());

        }
    };
    private Emitter.Listener onPlayerLeft = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            PlayerLeftResp playerLeftResp = MyDeserializer.desPlayerLeftResp(args[0].toString());


        }
    };
    private Emitter.Listener onPlayerRaise = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            PlayerRaiseResp playerRaiseResp = MyDeserializer.desPlayerRaiseResp(args[0].toString());

        }
    };
    private Emitter.Listener onPlayerStops = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String name = MyDeserializer.desPlayerStopsRespName(args[0].toString());

        }
    };
    private Emitter.Listener onPlayerRestore = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String name = MyDeserializer.desPlayerRestoresRespName(args[0].toString());

        }
    };
    private Emitter.Listener onRestore = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            RestoreResp restoreResp = MyDeserializer.desRestoreResp(args[0].toString());

        }
    };
    private Emitter.Listener onYouAllIn = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            YouAllInResp youAllInResp = MyDeserializer.desYouAllInResp(args[0].toString());

        }
    };
    private Emitter.Listener onYouCheck = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            YouCheckResp youCheckResp = MyDeserializer.desYouCheckResp(args[0].toString());

        }
    };
    private Emitter.Listener onYouFold = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            YouFoldResp youFoldResp = MyDeserializer.desYouFoldResp(args[0].toString());

        }
    };
    private Emitter.Listener onYouRaise = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            YouRaiseResp youRaiseResp = MyDeserializer.desYouRaiseResp(args[0].toString());

        }
    };
    private Emitter.Listener onYouLeft = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Integer myMoney = MyDeserializer.desYouLeft(args[0].toString());

        }
    };
    private Emitter.Listener onEndGame = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            EndgameResp endgameResp = MyDeserializer.desEndgameResp(args[0].toString());

        }
    };

    @Override
    public void sendMessageOnServerFold() {

    }

    @Override
    public void sendMessageOnServerCheck() {

    }

    @Override
    public void sendMessageOnServerRaise(int rate) {

    }

    @Override
    public void sendMessageOnServerAllIn() {

    }

    @Override
    public void sendMessageOnServerLeave() {

    }

    @Override
    public void sendMessageOnServerStop() {
        socket.emit("stop"
                ,GetJSON.nameLobbynameToken(
                        this.PLAYER_NAME
                        ,this.ROOM_NAME
                        ,this.SESSION_TOKEN
                )
        );
    }

    @Override
    public void sendMessageOnServerRestore() {
        socket.emit("restore"
                ,GetJSON.nameLobbynameToken(
                        this.ROOM_NAME
                        ,this.ROOM_NAME
                        ,this.SESSION_TOKEN
                )
        );
    }

    @Override
    public void sendMessageOnServerHandPower(long power) {
        socket.emit("myhandpower"
                ,GetJSON.nameTokenPower(
                        this.PLAYER_NAME
                        ,this.SESSION_TOKEN
                        ,power
                )
        );
    }

    @Override
    public void sendMessageOnServerEnterLobby(String roomName) {
        this.ROOM_NAME = roomName;
        socket.emit("enterlobby",
                GetJSON.nameLobbynameToken(
                        this.PLAYER_NAME
                        ,this.ROOM_NAME
                        ,this.SESSION_TOKEN
                )
        );
    }
}
