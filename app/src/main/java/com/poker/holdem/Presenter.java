package com.poker.holdem;

public class Presenter implements GameContract.Presenter {
    private GameContract.View mView;
    private GameContract.Logic mLogic;

    Presenter(GameContract.View view){
        this.mView = view;
        this.mLogic = new Model();
    }

    @Override
    public void foldButtonClicked() {
        mLogic.sendMessageOnServerFold();
    }
    @Override
    public void checkButtonClicked() {
        mLogic.sendMessageOnServerCheck();
    }

    @Override
    public void raiseButtonClicked(int rate) {
        mLogic.sendMessageOnServerRaise(rate);
    }

    @Override
    public void exitButtonClicked() {
        mLogic.sendMessageOnServerExit();
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
