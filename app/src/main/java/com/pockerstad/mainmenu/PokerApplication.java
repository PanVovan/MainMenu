package com.pockerstad.mainmenu;

import android.app.Application;
import android.content.SharedPreferences;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;


public class PokerApplication extends Application {
    private Socket socket;

    public Socket getSocket() throws URISyntaxException {
        if (socket != null){
            return socket;
        }
        else {
            return IO.socket("http://s-my-poker.herokuapp.com");
        }
    }

    SharedPreferences preferences;
}
