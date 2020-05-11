package com.poker.holdem;

public class Presenter implements GameContract.Presenter {

    private GameContract.View gameView;
    private GameContract.Server serverController;

    public Presenter(GameContract.View view){

        this.serverController = new ServerController(this) ;
        this.gameView = view;
    }



    //Тут то, что мы получаем от GameViewFragment
    @Override
    public void foldButtonClicked() {
        serverController.sendMessageOnServerFold();
    }

    @Override
    public void checkButtonClicked() {
        serverController.sendMessageOnServerCheck();
    }

    @Override
    public void raiseButtonClicked(int rate) {
        serverController.sendMessageOnServerRaise(rate);
    }

    @Override
    public void exitButtonClicked() {
        serverController.sendMessageOnServerLeave();
    }


    //То, что мы получаем от сервера
    @Override
    public void acceptMessageFromServerNewPlayerJoin() {

    }

    @Override
    public void acceptMessageFromServerOpponentCheck(String name) {

    }

    @Override
    public void acceptMessageFromServerOpponentRaise(String name) {

    }

    @Override
    public void acceptMessageFromServerOpponentAllIn(String name) {

    }

    @Override
    public void acceptMessageFromServerOpponentFold(String name) {

    }

    @Override
    public void acceptMessageFromServerOpponentLeft(String name) {

    }

    @Override
    public void acceptMessageFromServerAddCommunityCard(int card) {

    }

    @Override
    public void acceptMessageFromServerAddCard(int player, int card) {

    }
}
