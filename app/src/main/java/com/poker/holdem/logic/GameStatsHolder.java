package com.poker.holdem.logic;

import com.poker.holdem.logic.handlogic.Hand;
import com.poker.holdem.logic.handlogic.card.Card;
import com.poker.holdem.logic.handlogic.combination.HandClassifier;
import com.poker.holdem.logic.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;


public class GameStatsHolder {
    private Player mainPlayer;
    private String mainPlayerName;
    private List<Player> players = new ArrayList<>();
    private List<Integer> deck = new ArrayList<>();
    private Integer bank;
    private Integer rate;
    private String lead;
    private int roundsNum;

    public void onPlayerRaise(String name, int newRate){
        this.rate = newRate; playerSpendMoney(name, rate);
    }
    public void onPlayerFold(String name){  }
    public void onPlayerCheck(String name){ playerSpendMoney(name, rate); }
    public void onPlayerAllIn(String name){
        playerSpendMoney(name, getPlayerByName(name).getMoney());
    }
    public void onPlayerStop(String name){}
    public void onPlayerRestore(String name){}
    public void onEndGame(int win_val, List<String> winners){
        this.bank = 0;
        this.players.forEach((i)->{
            if(winners.contains(i.getName()))
                i.setMoney(i.getMoney()+win_val);
        });
    }
    public void addNewPlayer(Player player){
        player.setActive(false);
        player.setCards(new ArrayList<>());
        this.players.add(player);
    }


    public void setGame(
            List<Player> allplayers
            ,List<Player> activePlayers
            ,Map<String, List<Integer>> playersCardsMap
            ,String mainName
            ,List<Integer> cardsOnTable
            ,String lead
            ,Integer base_rate
            ,Integer bank
            ,Integer roundNum
    ){
        this.roundsNum = roundNum;
        this.bank = bank;
        //иницивлизируем так <- не всегда
        //List<> это ArrayList<>
        players = allplayers;//new ArrayList<>(allplayers);
        deck = new ArrayList<>(cardsOnTable);
        this.rate = base_rate;
        this.lead = lead;
        //предопределяем всех неактивными
        //и не совершившими ход
        for (Player i: players)
            i.setActive(false);
        //делаем активными всех, кто играет
        setActivePlayers(activePlayers);
        //...
        setGamePlayersCards(playersCardsMap);
        //чтобы каждый раз не искать
        mainPlayer = getPlayerByName(mainName);
        //Logger.getAnonymousLogger().info("name: "+mainName);
        mainPlayerName = mainName;
    }

    //ищем игрока и забираем у него деньги
    private void playerSpendMoney(String name, int val){
        this.players.forEach((i)->{
            if(i.getName().equals(name)){
                i.setMoney(i.getMoney()-val);
                this.bank+=val;
            }
        });
        if(name.equals(mainPlayerName))
            mainPlayer = getPlayerByName(mainPlayerName);
    }

    public void clearAllPlayersCards(){
        this.players.forEach((i)-> i.setCards(new ArrayList<>()));
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
        mainPlayer = getPlayerByName(mainPlayerName);
    }

    private void setPlayerActivity(String name, boolean activity){
        for(Player i: players)
            if(i.getName().equals(name))
                i.setActive(activity);
        if(name.equals(mainPlayerName))
            mainPlayer = getPlayerByName(mainPlayerName);
    }

    public void setPlayerPos(String  name, int pos){
        this.players.forEach((i)->{
            if(i.getName().equals(name)) i.setPos(pos);
        });
        if(name.equals(mainPlayerName))
            mainPlayer = getPlayerByName(mainPlayerName);
    }

    public boolean checkIfPlaceIsNotTaken(int pos){
        for (int i=0; i<this.players.size(); i++)
            if(this.players.get(i).getPos()==pos)
                return false;
            return true;
    }

    //private void setPlayerDidSomething(String name){
    //    for(Player i: players)
    //        if(i.getName().equals(name))
    //            i.setDidSomethingInThisRound(true);
    //}

    public void setPlayerCards(String name, List<Integer> cards){
        for(Player i: players)
            if(i.getName().equals(name))
                i.setCards(cards);
        if(name.equals(mainPlayerName))
            mainPlayer = getPlayerByName(mainPlayerName);
    }

    public Player getPlayerByName(String name){
        Player result = null;
        for (int i=0; i<players.size(); i++)
            if(players.get(i).getName().equals(name))
                result =  players.get(i);
        return result;
    }

    //переименовал в getMainPlayerHandPower, так понятнее
    public long getMainPlayerHandPower(int count) {
        Hand.Builder builder = new Hand.Builder();

        for (int i = 0; i<count && i<6; i++)
            builder.addCommunityCard(Optional.of(new Card(deck.get(i))));

        for (Integer card: getPlayerByName(mainPlayerName).getCards())
            builder.addHoleCard(Optional.of(new Card(card)));

        return HandClassifier.getPowerStatic(builder.build());
    }

    public boolean mainPlayerIsInGame(){ return getPlayerByName(mainPlayerName).isActive(); }

    //TODO: метка
    public void deleteOpponent(String name) {
        players.remove(getPlayerByName(name));
    }

    public Player getMainPlayer(){
        //Logger.getAnonymousLogger().info("mainPlayername"+mainPlayerName+ " ");
        return getPlayerByName(mainPlayerName); }

    public int getPosPlayer(String name){
        for(int i=0; i<players.size(); i++) {
            if (name.equals(players.get(i).getName()))
                return i;
        }
        return -1;
    }

    public Integer getBank() { return bank; }

    public void setBank(Integer bank) { this.bank = bank; }

    public Integer getRate() { return rate;  }

    public void setRate(Integer rate) { this.rate = rate; }

    public String getLead() { return lead; }

    public void setLead(String lead) { this.lead = lead; }

    public List<Integer> getDeck() { return deck; }

    public void setDeck(List<Integer> deck) { this.deck = deck; }

    public List<Player> getPlayers() { return players; }

    public void setPlayers(List<Player> players) { this.players = players; }

    public int getRoundsNum() { return roundsNum;  }

    public void setRoundsNum(int roundsNum) { this.roundsNum = roundsNum; }

    public void increaseRoundsNum(){ this.roundsNum++; }

    public String getMainPlayerName() {
        return mainPlayerName;
    }

    public void setMainPlayerName(String mainPlayerName) {
        this.mainPlayerName = mainPlayerName;
    }
}
