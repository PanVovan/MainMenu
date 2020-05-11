package com.poker.holdem.server.mysocketholder;



//КЛАСС ДУБЛИРУЕТ PokerApplicationManager


import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
/*
public class SocketUtil {

    private static final String SERVER_ADDR = "https://tryserv1.herokuapp.com";

    private Socket mSocket;

    public Socket getSocket(){
        if (mSocket != null){
            return mSocket;
        }
        else {
            try {
                mSocket = IO.socket(SERVER_ADDR);
                return mSocket;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

}*/
