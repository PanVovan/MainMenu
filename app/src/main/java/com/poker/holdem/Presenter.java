package com.poker.holdem;

import android.content.Context;

import com.poker.holdem.constants.Constants;
import com.poker.holdem.logic.GameStatsHolder;
import com.poker.holdem.logic.handlogic.Hand;
import com.poker.holdem.logic.player.Player;
import com.poker.holdem.view.util.ViewControllerActionCode;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Logger;

public class Presenter implements GameContract.Presenter {

    private GameContract.View gameView;
    private GameContract.Server serverController;

    private Hand.Builder handBuilder;

    private String ROOM_NAME = "";
    private String PLAYER_NAME = "";

    //просто контейнер для кучи значений
    private GameStatsHolder gameStats;

    public Presenter(GameContract.View view, String roomName){
        this.ROOM_NAME = roomName;
        this.PLAYER_NAME = PokerApplicationManager
                .getInstance()
                .getSharedPreferences(
                        Constants.PREFS_NAME
                        ,Context.MODE_PRIVATE
                ).getString(Constants.PLAYER_NAME, "");
        this.handBuilder = new Hand.Builder();
        this.serverController = new ServerController(this) ;
        this.gameView = view;
        this.serverController.sendMessageOnServerEnterLobby(this.ROOM_NAME);

        gameStats = new GameStatsHolder();
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
        serverController.disconnect();
    }

    //То, что мы получаем от сервера
    @Override
    public void acceptMessageFromServerNewPlayerJoin(Player player) {

    }
    @Override
    public void acceptMessageFromServerOpponentCheck(String name, String newLead) {

    }
    @Override
    public void acceptMessageFromServerOpponentRaise(String name, Integer rate, String newLead) {

    }
    @Override
    public void acceptMessageFromServerOpponentAllIn(String name, String newLead) {

    }
    @Override
    public void acceptMessageFromServerOpponentFold(String name, String newLead) {

    }
    @Override
    public void acceptMessageFromServerOpponentLeft(String name, String newLead) {
        gameStats.deleteOpponent(name);
        switch (gameStats.getPosPlayer(name)){
            case 0:
                gameView.clearCards(ViewControllerActionCode.CLEAR_FIRST_OPPONENT_CARDS);
                gameView.clearOpponentView(1);
                break;
            case 1:
                gameView.clearCards(ViewControllerActionCode.CLEAR_SECOND_OPPONENT_CARDS);
                gameView.clearOpponentView(2);
                break;
            case 2:
                gameView.clearCards(ViewControllerActionCode.CLEAR_THIRD_OPPONENT_CARDS);
                gameView.clearOpponentView(3);
                break;
            case 3:
                gameView.clearCards(ViewControllerActionCode.CLEAR_FOURTH_OPPONENT_CARDS);
                gameView.clearOpponentView(4);
                break;
        }
    }
    @Override
    public void acceptMessageFromServerOpponentStop(String name) {

    }
    @Override
    public void acceptMessageFromServerOpponentRestore(String name) {

    }
    @Override
    public void acceptMessageFromServerEndGame(Integer winVal, List<String> winners) {

    }

    //TODO: сделать нормально
    //@Override
    //public void acceptMessageFromServerAddCommunityCard(int card) {
    //    handBuilder.addCommunityCard(Optional.of(new Card(card)));
    //    switch (handBuilder.getCommunityCards().size()){
    //        case 1:
    //            gameView.setCardView(ViewControllerActionCode.ADD_COMMUNITY_CARD_FIRST, card);
    //            break;
    //        case 2:
    //            gameView.setCardView(ViewControllerActionCode.ADD_COMMUNITY_CARD_SECOND, card);
    //            break;
    //        case 3:
    //            gameView.setCardView(ViewControllerActionCode.ADD_COMMUNITY_CARD_THIRD, card);
    //            break;
    //        case 4:
    //            gameView.setCardView(ViewControllerActionCode.ADD_COMMUNITY_CARD_FOURTH, card);
    //            break;
    //        case 5:
    //            gameView.setCardView(ViewControllerActionCode.ADD_COMMUNITY_CARD_FIFTH, card);
    //            break;
    //    }
    //}

    //TODO:не знаю, нужен ли этот метод
    //сервер выдаёт карты большими партиями, и лучше
    //это VV организовать в acceptMessageFromServerEnterLobby и т.п.
    //TODO: извлечь из префов имя игрока, или как что то еще в зависимость от сервера
    //имя извлечено
    // @Override
    //public void acceptMessageFromServerAddCard(String name, int card) {
        /*String playername = PokerApplicationManager
                .getInstance()
                .getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
                .getString(Constants.PLAYER_NAME, "");
        if (name.equals(playername)){
            handBuilder.addHoleCard(Optional.of(new Card(card)));
            switch (handBuilder.build().size()){
                case 1:
                    gameView.setCardView(ViewControllerActionCode.ADD_FIRST_PLAYER_CARD, card);
                    break;
                case 2:
                    gameView.setCardView(ViewControllerActionCode.ADD_SECOND_PLAYER_CARD, card);
                    break;
            }
        }
        //Тут мы проходим массив игроков
        else {
            for (int i = 0; i < players.size(); i++){
                if (players.get(i).getName().equals(name)){
                    if(card != ViewControllerActionCode.NONE) {
                        players.get(i).getCards().add(card);
                    }
                    switch (i) {
                        case 0:
                            switch (players.get(i).getCards().size()){
                                case 1:
                                    gameView.setCardView(ViewControllerActionCode.ADD_FIRST_OPPONENT_FIRST_CARD, card);
                                    break;
                                case 2:
                                    gameView.setCardView(ViewControllerActionCode.ADD_FIRST_OPPONENT_SECOND_CARD, card);
                                    break;
                            }
                            break;
                        case 1:
                            switch (players.get(i).getCards().size()){
                                case 1:
                                    gameView.setCardView(ViewControllerActionCode.ADD_SECOND_OPPONENT_FIRST_CARD, card);
                                    break;
                                case 2:
                                    gameView.setCardView(ViewControllerActionCode.ADD_SECOND_OPPONENT_SECOND_CARD, card);
                                    break;
                            }
                            break;
                        case 2:
                            switch (players.get(i).getCards().size()){
                                case 1:
                                    gameView.setCardView(ViewControllerActionCode.ADD_THIRD_OPPONENT_FIRST_CARD, card);
                                    break;
                                case 2:
                                    gameView.setCardView(ViewControllerActionCode.ADD_THIRD_OPPONENT_SECOND_CARD, card);
                                    break;
                            }
                            break;
                        case 3:
                            switch (players.get(i).getCards().size()){
                                case 1:
                                    gameView.setCardView(ViewControllerActionCode.ADD_FOURTH_OPPONENT_FIRST_CARD, card);
                                    break;
                                case 2:
                                    gameView.setCardView(ViewControllerActionCode.ADD_FOURTH_OPPONENT_SECOND_CARD, card);
                                    break;
                            }
                            break;
                    }
                }
            }
        }*/
    //}

    @Override
    public void acceptMessageFromServerEnterLobby(
            List<Player> allplayers
            ,List<Player> gameplayers
            ,List<Integer> deck
            ,Map<String, List<Integer>> playersCardsMap
            ,String lead
            ,Integer base_rate
            ,Integer rounds_done
    ) {
        Logger.getAnonymousLogger().info("<--------Entered lobby!");
        gameStats.setBank(0);
        gameStats.setLead(lead);
        gameStats.setRate(base_rate);
        gameStats.setDeck(deck);

        //сначала сделал отдельными методами, но потом решил вынести
        gameStats.initPlayers(
                allplayers
                ,gameplayers
                ,playersCardsMap
                ,this.PLAYER_NAME
        );

        gameView.setPlayerView(gameStats.getMainPlayer());

        //вот так мы расставляем игроков
        Stack<Player> opponents = (Stack<Player>) gameStats.getPlayers();
        //удаляем основного, его уже поставили
        opponents.remove(gameStats.getMainPlayer());
        //т.к. мы снимаем сверху, то разворачиваем стек
        //чтобы игроки располагались по часовой
        //это влияет на порядок хода
        Collections.reverse(opponents);
        for(int i=0; i<4; i++){
            if(opponents.isEmpty())
                break;
            gameView.setOpponentView(i, opponents.pop());
        }
        //TODO:убрать костыль
        //с новым методом GameContract.View showCommunityCards
        gameStats.setNumberOfCardsOpened(rounds_done);
        //типа по количеству раундов ставим но
        for (int i = 0; i<gameStats.getNumberOfCardsOpened() && i<4; i++)
            gameView.setCardView(
                    //за костыль извиняюсь, но я хз как это
                    //отобразить без больших свичей
                    101+i
                    ,gameStats.getDeck().get(i)
            );
    }
    @Override
    public void acceptMessageFromServerRestore(
            List<Player> allplayers
            ,List<Player> gameplayers
            ,List<Integer> deck
            ,Map<String, List<Integer>> playersCardsMap
            ,String lead
            ,Integer rate
            ,Integer rounds_done
    ) {

    }
    @Override
    public void acceptMessageFromServerGameStarts(
            List<Player> allplayers
            ,List<Player> gameplayers
            ,List<Integer> deck
            ,Map<String,List<Integer>> playersCardsMap
            ,String lead
            ,Integer base_rate
            ,Integer rounds_done
    ) {

    }

}