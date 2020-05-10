package com.pockerstad.mainmenu.view;

import com.pockerstad.mainmenu.GameContract;
import com.pockerstad.mainmenu.players.utils.Player;

public class Model implements GameContract.Model {
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
