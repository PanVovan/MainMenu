package com.poker.holdem;

public interface LobbyContract {

    interface MenuLobbies{
        void sendMessageOnServerGetLobbies();
    }

    interface Registrator{
        void sendMessageOnServerRegister();
        void sendMessageOnServerAuthToken(String name, String authToken);
        void sendMessageOnServerAuthPassword(String name, String password);
    }
}
