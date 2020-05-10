package com.poker.holdem;

import com.poker.holdem.players.utils.Player;

public class Model implements GameContract.Logic {
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
    public void sendMessageOnServerExit() {

    }
}
