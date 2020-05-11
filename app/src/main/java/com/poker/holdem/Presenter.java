package com.poker.holdem;

import com.poker.holdem.logic.GameLogic;

public class Presenter implements GameContract.Presenter {
    private GameContract.View mView;
    private GameContract.Logic mLogic;

    public Presenter(GameContract.View view){
        this.mView = view;
        this.mLogic = new GameLogic();
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
        mLogic.sendMessageOnServerLeave();
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
