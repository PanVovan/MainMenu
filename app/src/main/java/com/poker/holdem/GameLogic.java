package com.poker.holdem;

import com.poker.holdem.players.utils.Player;

import io.socket.client.Socket;

public class GameLogic implements GameContract.Logic {


    private Socket socket;

    GameLogic(){
        socket = PokerApplicationManager.getInstance().getSocket();
    }

    @Override
    public int getCard() {
        return 0;
    }

    @Override
    public Player getPlayer() {

        return null;
    }

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

    }

    @Override
    public void sendMessageOnServerRestore() {

    }

    @Override
    public void sendMessageOnServerHandPower() {

    }

    @Override
    public void sendMessageOnServerEnterLobby() {

    }

}
