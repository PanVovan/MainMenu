package com.poker.holdem;

import com.poker.holdem.logic.MyThread;

public class ServerController implements GameContract.Server, Runnable {

    GameContract.Presenter presenter;
    private MyThread handlerThread;

    //Скорее всего он понадобится для прослушивания, но может и нет, я не шарю как это работает
    private Thread listenThread;

    //На всякий, если не понадобится
    @Override
    public void run() {

    }



    ServerController(GameContract.Presenter presenter){
        /*
        * Тут мы ставим слушатели сокетов
        * Лучше создать для этого метод
        */

        this.handlerThread = new MyThread("Server Thread");
        this.presenter = presenter;
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
