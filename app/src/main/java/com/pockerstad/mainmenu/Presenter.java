package com.pockerstad.mainmenu;

import com.pockerstad.mainmenu.view.Model;

public class Presenter implements GameContract.Presenter {
    private GameContract.View mView;
    private GameContract.Model mModel;

    Presenter(GameContract.View view){
        this.mView = view;
        this.mModel = new Model();
    }

    @Override
    public void foldButtonClicked() {

    }

    @Override
    public void checkButtonClicked() {

    }

    @Override
    public void raiseButtonClicked(int rate) {
        mModel.sendMessageOnServerRaise(rate);
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
