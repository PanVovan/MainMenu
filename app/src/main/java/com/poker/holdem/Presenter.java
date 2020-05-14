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
        gameStats.setLead(newLead);
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