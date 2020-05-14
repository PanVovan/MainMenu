package com.poker.holdem.logic;

import com.poker.holdem.logic.handlogic.Hand;
import com.poker.holdem.logic.handlogic.card.Card;
import com.poker.holdem.logic.handlogic.combination.HandClassifier;
import com.poker.holdem.logic.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class GameStatsHolder {
    private Player mainPlayer;
    private List<Player> players = new ArrayList<>();
    private List<Integer> deck = new ArrayList<>();
    private Integer bank;
    private Integer rate;
    private String lead;
    private int numberOfCardsOpened;

    public void onPlayerCheck(String name){

    }





    public void initPlayers(
            List<Player> allplayers
            ,List<Player> activePlayers
            ,Map<String, List<Integer>> playersCardsMap
            ,String mainName
    ){
        players = allplayers;

        //предопределяем всех неактивными
        //и не совершившими ход
        for (Player i: players) {
            i.setActive(false);
            i.setDidSomethingInThisRound(false);
        }
        //делаем активными всех, кто играет
        setActivePlayers(activePlayers);
        //...
        setGamePlayersCards(playersCardsMap);
        //чтобы каждый раз не искать
        mainPlayer = getPlayerByName(mainName);
    }

    public void onGameAction(String actorName){
        setPlayerDidSomething(actorName);

    }

    public void onpPlayerLeaves(String actorName){

    }

    public void onPlayerFold(String actorName){

    }

    private void setGamePlayersCards(Map<String, List<Integer>> cards){
        for(Player i: players)
            if(i.isActive())
                i.setCards(
                        cards.get(i.getName())
                );
    }

    private void setActivePlayers(List<Player> gamePlayers){
        for(Player i: gamePlayers)
            setPlayerActivity(i.getName(), true);
    }

    private void setPlayerActivity(String name, boolean activity){
        for(Player i: players)
            if(i.getName().equals(name))
                i.setActive(activity);
    }

    private void setPlayerDidSomething(String name){
        for(Player i: players)
            if(i.getName().equals(name))
                i.setDidSomethingInThisRound(true);
    }

    public void setPlayerCards(String name, List<Integer> cards){
        for(Player i: players)
            if(i.getName().equals(name))
                i.setCards(cards);
    }

    public Player getPlayerByName(String name){
        for(Player i: players)
            if (i.getName().equals(name))
                return i;
        return null;
    }


    public long getHandPower(int count) {
        Hand.Builder builder = new Hand.Builder();
        for (int i = 0; i<count; i++){
            builder.addCommunityCard(Optional.of(new Card(deck.get(i))));
        }
        for (Integer card: mainPlayer.getCards()){
            builder.addHoleCard(Optional.of(new Card(card)));
        }

        return HandClassifier.getPowerStatic(builder.build());

    }

    //TODO: метка
    public void deleteOpponent(String name) {
        players.remove(getPlayerByName(name));
    }

    public Player getMainPlayer(){ return mainPlayer; }

    public int getPosPlayer(String name){
        return players.indexOf(name);
    }

    public Integer getBank() {
        return bank;
    }

    public void setBank(Integer bank) {
        this.bank = bank;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public List<Integer> getDeck() {
        return deck;
    }

    public void setDeck(List<Integer> deck) {
        this.deck = deck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNumberOfCardsOpened() {
        return numberOfCardsOpened;
    }

    public void setNumberOfCardsOpened(int numberOfCardsOpened) {
        this.numberOfCardsOpened = numberOfCardsOpened;
    }
}
