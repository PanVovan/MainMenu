package com.poker.holdem.logic;

import com.poker.holdem.logic.handlogic.Hand;
import com.poker.holdem.logic.handlogic.card.Card;
import com.poker.holdem.logic.handlogic.combination.HandClassifier;
import com.poker.holdem.logic.handlogic.combination.HandCombination;
import com.poker.holdem.logic.player.Player;
import com.poker.holdem.view.util.ViewControllerActionCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameStatsHolder {
    private Player mainPlayer;
    private String mainPlayerName;
    private List<Player> players = new ArrayList<>();
    private List<Integer> deck = new ArrayList<>();
    private Integer bank = 0;
    private Integer rate = 0;
    private String lead = "";
    private int roundsNum = 0;
    private List<String> listOfPlayersReadyToFinishGame = new ArrayList<>();
    private int cardsOpened = 0;
    private boolean isGameRunning = false;
    //индикатор того, что игрок что-то сделал, и в этом ходу ему не надо больше ничего
    //делать; это проверка "а не отправили ли мы уже что-нибудь, в сервер ещё не ответил?";
    //игрок может быстро нажать кнопку несколько раз, пока ответ с сервера
    //будет идти; это может плохо сказаться как на работе клиента,
    //так и на работе сервера
    private boolean playerPushedButton = false;

    //нам нужно показать во вью карты игроков-победителей
    public HashMap<Integer, List<Integer> > getWinningPlayersCards(List<String> winners){
        return (HashMap<Integer, List<Integer>>)this.players
                .stream()
                .filter(x -> winners.contains(x.getName()))
                .collect(Collectors.toMap(Player::getPos, Player::getCards));
    }

    private void onNewRound(){
        //если все готовы (нет денег/пошли ва-банк)
        //то удалять никого не надо, т.к. игра заканчивается
        boolean isGameEnd = this.listOfPlayersReadyToFinishGame.size()
                                    == this.players.size();
        if(!isGameEnd) {
            //удалить всех, кто в прошлом раунде
            //сделал all in или fold
            players.forEach((x)->{
                if(listOfPlayersReadyToFinishGame.contains(x.getName()))
                    x.setActive(false);
            });
        }
    }

    public void playerGetsMoney(String name, int val){
        this.players.forEach(i->{
            if(i.getName().equals(name))
                i.setMoney(i.getMoney()+val);
        });
    }
    public void onMeSpendMoney(int val){
        playerSpendMoney(this.mainPlayerName, val);
        //если закончились деньги, мы должны стать неактивны
        if(this.getPlayerMoney(mainPlayerName) <= 0)
            this.setPlayerActivity(mainPlayerName, false);
    }
    public void onPlayerRaise(String name, int newRate){
        this.rate = newRate;
        //ACHTUNG! когда мы делаем рейз, то с сервера придёт
        //имя игрока (наше)
        //но в этом случае ставка УЖЕ БУДЕТ ВЫЧТЕНА (Presenter.raiseButtonClicked())
        if (!name.equals(mainPlayerName))
            playerSpendMoney(name, rate);
    }
    public void onPlayerFold(String name){
        setPlayerActivity(name, false);
    }
    public void onPlayerCheck(String name){ playerSpendMoney(name, rate); }
    public void onPlayerAllIn(String name){
        this.listOfPlayersReadyToFinishGame.add(name);
        playerSpendMoney(name, getPlayerByName(name).getMoney());
    }
    public void onPlayerStop(String name){}
    public void onPlayerRestore(String name){}
    public void onEndGame(int win_val, List<String> winners){
        this.isGameRunning = false;
        this.listOfPlayersReadyToFinishGame = new ArrayList<>();
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
        this.isGameRunning = true;
        this.playerPushedButton = false;
        this.cardsOpened = 3;
        this.listOfPlayersReadyToFinishGame = new ArrayList<>();
        this.roundsNum = roundNum;
        this.bank = bank;
        //иницивлизируем так <- не всегда
        //List<> это ArrayList<>
        this.players = allplayers;//new ArrayList<>(allplayers);
        this.deck = new ArrayList<>(cardsOnTable);
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
        this.mainPlayer = getPlayerByName(mainName);
        //Logger.getAnonymousLogger().info("name: "+mainName);
        this.mainPlayerName = mainName;
        this.players.forEach((i)->{
            if(i.getName().equals(mainName))
                i.setPos(ViewControllerActionCode.POSITION_MAIN_PLAYER);
        });
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

    public int getPlayerMoney(String name){
        for(int i=0; i<this.players.size(); i++)
            if(this.players.get(i).getName().equals(name))
                return this.players.get(i).getMoney();
            return -1;
    }

    public void clearAllPlayersCards(){
        this.players.forEach( i -> i.setCards(new ArrayList<>()));
    }

    private void setGamePlayersCards(Map<String, List<Integer>> cards){
        for(Player i: players)
            if(i.isActive())
                i.setCards(
                        cards.get(i.getName()));
    }

    private void setActivePlayers(List<Player> gamePlayers){
        for(Player i: gamePlayers)
            setPlayerActivity(i.getName(), true);
        mainPlayer = getPlayerByName(mainPlayerName);
    }

    public void setPlayerActivity(String name, boolean activity){
        for(Player i: players) {
            if (i.getName().equals(name))
                i.setActive(activity);
        }
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

    public HandCombination getMainPlayerHandCombinationRank(int count) {
        Hand.Builder builder = new Hand.Builder();

        for (int i = 0; i<count && i<6; i++)
            builder.addCommunityCard(Optional.of(new Card(deck.get(i))));

        for (Integer card: getPlayerByName(mainPlayerName).getCards())
            builder.addHoleCard(Optional.of(new Card(card)));

        return HandClassifier.classifyPokerHand(builder.build()).getCombinationRank();
    }

    public boolean mainPlayerIsInGame(){ return getPlayerByName(mainPlayerName).isActive(); }

    public void deleteOpponent(String name) {
        players.remove(getPlayerByName(name));
    }

    public Player getMainPlayer(){
        //Logger.getAnonymousLogger().info("mainPlayername"+mainPlayerName+ " ");
        return getPlayerByName(mainPlayerName); }

    public int getPosPlayer(String name){
        for(int i=0; i<players.size(); i++) {
            if (name.equals(players.get(i).getName()))
                return players.get(i).getPos();
        }
        return -1;
    }

    public Integer getBank() { return bank; }

    public void setBank(Integer bank) { this.bank = bank; }

    public Integer getRate() { return rate; }

    public void setRate(Integer rate) { this.rate = rate; }

    public String getLead() { return lead==null ?"":lead; }

    public void setLead(String lead) { this.lead = lead; }

    public List<Integer> getDeck() { return deck; }

    public void setDeck(List<Integer> deck) { this.deck = deck; }

    public List<Player> getPlayers() { return players; }

    public void setPlayers(List<Player> players) { this.players = players; }

    public int getRoundsNum() { return roundsNum;  }

    public void setRoundsNum(int roundsNum) { this.roundsNum = roundsNum; }

    public void increaseRoundsNum(){ this.roundsNum++; onNewRound(); }

    public String getMainPlayerName() {
        return mainPlayerName;
    }

    public void setMainPlayerName(String mainPlayerName) {
        this.mainPlayerName = mainPlayerName;
    }

    public int getCardsOpened() {
        return cardsOpened;
    }

    public void setCardsOpened(int cardsOpened) {
        this.cardsOpened = cardsOpened;
    }

    public boolean isPlayerPushedButton() {
        return playerPushedButton;
    }

    public void setPlayerPushedButton(boolean playerPushedButton) {
        this.playerPushedButton = playerPushedButton;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }
}
