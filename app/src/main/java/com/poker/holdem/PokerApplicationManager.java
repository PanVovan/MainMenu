package com.poker.holdem;

import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;


public class PokerApplicationManager extends Application {
    private static final String SERVER_ADDR = "https://tryserv1.herokuapp.com";

    private Socket socket;

    public Socket getSocket(){
        if (socket != null){
            return socket;
        }
        else {
            try {
                socket = IO.socket(SERVER_ADDR);
                return socket;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
