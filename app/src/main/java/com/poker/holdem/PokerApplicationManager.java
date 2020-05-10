package com.poker.holdem;

import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;


public class PokerApplicationManager extends Application {
    private Socket socket;

    public Socket getSocket() throws URISyntaxException {
        if (socket != null){
            return socket;
        }
        else {
            socket = IO.socket("http://s-my-poker.herokuapp.com");
            return socket;
        }
    }
}
