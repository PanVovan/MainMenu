package com.poker.holdem;

public class Presenter implements GameContract.Presenter {
    private GameContract.View mView;
    private GameContract.Logic mLogic;

    Presenter(GameContract.View view){
        this.mView = view;
        this.mLogic = new GameLogic();
    }

    @Override
    public void foldButtonClicked() {

    }

    @Override
    public void checkButtonClicked() {

    }

    @Override
    public void raiseButtonClicked(int rate) {
        mLogic.sendMessageOnServerRaise(rate);
    }

    @Override
    public void exitButtonClicked() {

    }

    @Override
    public void setCard(int action, int card) {

    }

    @Override
    public void clearCards(int typeOfClear) {
        mView.clearCards(typeOfClear);
    }

    @Override
    public void setPlayer() {

    }

    @Override
    public void setOpponent() {

    }
}
