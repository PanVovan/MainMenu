package com.poker.holdem;

import androidx.fragment.app.Fragment;


public interface LobbyContract {

    interface MenuLobbies{
        void sendMessageOnServerGetLobbies();
    }

    interface Registrator{
        void sendMessageOnServerRegister(String name, String password, Integer picture, Fragment fragment);
        void sendMessageOnServerAuthToken(String name, String authToken);
        void sendMessageOnServerAuthPassword(String name, String password);
    }
}
