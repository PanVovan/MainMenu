package com.poker.holdem;

public interface LobbyContract {

    interface MenuLobbies{
        void sendMessageOnServerGetLobbies();
    }

    interface Registrator{
        void sendMessageOnServerRegister();
        void sendMessageOnServerAuth();
    }
}
