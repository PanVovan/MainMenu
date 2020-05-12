package com.poker.holdem.logic;

import com.poker.holdem.GameContract;
import com.poker.holdem.server.deserialization.gamestarts.GameStartsResp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//класс для отсчёта ходов
//пока не нужен
public class Ticker implements GameContract.Presenter {
    private String lead;
    private ArrayList<Integer> deck;
    private Map<String, List<Integer>> playersCards = new HashMap<>();

    public void setGame(GameStartsResp gameStartsResp){

    }

    private void setLead(String lead_name){
        this.lead = lead_name;
    }
    private void makeDeck(List<Integer> cards){
        this.deck = new ArrayList<>();
        this.deck.addAll(cards);
    }

    @Override
    public void foldButtonClicked() {

    }

    @Override
    public void checkButtonClicked() {

    }

    @Override
    public void raiseButtonClicked(int rate) {

    }

    @Override
    public void exitButtonClicked() {

    }

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
