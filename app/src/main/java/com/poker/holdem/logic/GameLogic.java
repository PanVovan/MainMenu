package com.poker.holdem.logic;

import com.poker.holdem.GameContract;
import com.poker.holdem.PokerApplicationManager;
import com.poker.holdem.logic.player.Player;

import io.socket.client.Socket;

public class GameLogic implements GameContract.Logic {


    private Socket socket;

    public GameLogic(){
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
