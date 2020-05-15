package com.poker.holdem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.poker.holdem.constants.Constants;
import com.poker.holdem.logic.GameStatsHolder;
import com.poker.holdem.logic.player.Player;
import com.poker.holdem.view.activity.MainActivity;
import com.poker.holdem.view.util.ViewControllerActionCode;

import java.util.ArrayList;
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
    public Integer exitButtonClicked(){           //Intent intent) {
        //intent.putExtra("money", gameStats.getPlayerByName(this.PLAYER_NAME).getMoney());
        serverController.sendMessageOnServerLeave();
        return gameStats.getPlayerByName(this.PLAYER_NAME).getMoney();

    }

    //То, что мы получаем от сервера
    @Override
    public void acceptMessageFromServerNewPlayerJoin(Player player) {
        if (gameStats.checkIfPlaceIsNotTaken(ViewControllerActionCode.POSITION_OPPONENT_FIRST))
            player.setPos(ViewControllerActionCode.POSITION_OPPONENT_FIRST);
        if (gameStats.checkIfPlaceIsNotTaken(ViewControllerActionCode.POSITION_OPPONENT_SECOND))
            player.setPos(ViewControllerActionCode.POSITION_OPPONENT_SECOND);
        if (gameStats.checkIfPlaceIsNotTaken(ViewControllerActionCode.POSITION_OPPONENT_THIRD))
            player.setPos(ViewControllerActionCode.POSITION_OPPONENT_THIRD);
        if (gameStats.checkIfPlaceIsNotTaken(ViewControllerActionCode.POSITION_OPPONENT_FOURTH))
            player.setPos(ViewControllerActionCode.POSITION_OPPONENT_FOURTH);
        gameStats.addNewPlayer(player);
        //вот это я сделал, чтобы избежать ошибок
        //если вот это gameStats.addNewPlayer(player); не пройдёт
        //то нам не покажется того, чего нет в gameStats
        player = gameStats.getPlayerByName(player.getName());
        gameView.setOpponentView(player.getPos(), player);

    }
    @Override
    public void acceptMessageFromServerOpponentCheck(String name, String newLead, boolean didRoundChange) {
        gameStats.onPlayerCheck(name);
        if(didRoundChange) gameStats.increaseRoundsNum();
        checkIfShouldOpenNewCard();
        gameStats.setLead(newLead);
        gameView.updatePlayerMoney(
                gameStats.getPlayerByName(name).getPos()
                ,gameStats.getPlayerByName(name).getMoney()
        );
    }
    @Override
    public void acceptMessageFromServerOpponentRaise(String name, Integer rate, String newLead, boolean didRoundChange) {
        gameStats.onPlayerRaise(name, rate);
        if(didRoundChange) gameStats.increaseRoundsNum();
        checkIfShouldOpenNewCard();
        gameStats.setLead(newLead);
        gameView.updatePlayerMoney(
                gameStats.getPlayerByName(name).getPos()
                ,gameStats.getPlayerByName(name).getMoney()
        );
    }
    @Override
    public void acceptMessageFromServerOpponentAllIn(String name, String newLead, boolean didRoundChange) {
        gameStats.onPlayerAllIn(name);
        if(didRoundChange) gameStats.increaseRoundsNum();
        checkIfShouldOpenNewCard();
        gameStats.setLead(newLead);
        gameView.updatePlayerMoney(
                gameStats.getPlayerByName(name).getPos()
                ,gameStats.getPlayerByName(name).getMoney()
        );
    }
    @Override
    public void acceptMessageFromServerOpponentFold(String name, String newLead, boolean didRoundChange) {
        gameStats.onPlayerFold(name);
        if(didRoundChange) gameStats.increaseRoundsNum();
        checkIfShouldOpenNewCard();
        gameStats.setLead(newLead);
        gameView.updatePlayerMoney(
                gameStats.getPlayerByName(name).getPos()
                ,gameStats.getPlayerByName(name).getMoney()
        );
    }
    @Override
    public void acceptMessageFromServerOpponentLefMeDidSomething(String nextLead, boolean didRoundChange){
        if(didRoundChange) gameStats.increaseRoundsNum();
        checkIfShouldOpenNewCard();
        gameStats.setLead(nextLead);
        gameView.updatePlayerMoney(
                gameStats.getPlayerByName(this.PLAYER_NAME).getPos()
                ,gameStats.getPlayerByName(this.PLAYER_NAME).getMoney()
        );
    }
    @Override
    public void acceptMessageFromServerOpponentLeft(String name, String newLead, boolean didRoundChange) {
        gameStats.deleteOpponent(name);
        if(didRoundChange) gameStats.increaseRoundsNum();
        checkIfShouldOpenNewCard();
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
        //тут можно сделать типа отображнеие того, что игрок отошел
        //а вообще пока всё равно
    }
    @Override
    public void acceptMessageFromServerOpponentRestore(String name) {
        //...
    }
    @Override
    public void acceptMessageFromServerEndGame(Integer winVal, List<String> winners) {
        gameStats.onEndGame(winVal, winners);
        showCardsWhenGameIsDone();

        //TODO: *романтическая пауза*
        //вот тут нужно сделать задержку, чтобы игрок полюбовался на
        //карты победителей (свои карты)

        //карт у игроков больше нет, убираем
        gameStats.clearAllPlayersCards();
        gameView.clearCards(ViewControllerActionCode.CLEAR_ALL_CARDS);

        //вот так нехитро обновляем деньги игроков
        gameStats.getPlayers().forEach((i) -> gameView.updatePlayerMoney(
                gameStats.getPlayerByName(i.getName()).getPos()
                , gameStats.getPlayerByName(i.getName()).getMoney()
        ));
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
            ,Integer bank
            ,boolean isgamerunning
    ) {
        //Logger logger = Logger.getAnonymousLogger();
        //logger.info("<--------Entered lobby!");
        //logger.info("My money: " + allplayers.get(0).getMoney());
        //allplayers.forEach((i)->{
        //    logger.info(i.getName()+"  "+i.getMoney());
        //});
        //сначала сделал отдельными методами, но потом решил вынести
        gameStats.setGame(
                allplayers
                ,gameplayers
                ,playersCardsMap
                ,this.PLAYER_NAME
                ,deck
                ,lead
                ,base_rate
                ,bank
                ,rounds_done
        );

        if(!isgamerunning) gameStats.setPlayerCards(gameStats.getMainPlayerName(), new ArrayList<>());
        gameView.setPlayerView(gameStats.getMainPlayer());
        gameStats.setPlayerPos(
                this.PLAYER_NAME
                ,ViewControllerActionCode.POSITION_MAIN_PLAYER
        );

        //TODO: ACHTUNG! тут мы показываем первые три карты на столе
        if(isgamerunning) {
            showFirstFreeCards();
            checkIfShouldOpenNewCard();
            sitThePlayers();
        }

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
            ,Integer bank
            ,boolean isgamerunning
    ) {
        gameStats.setGame(
                allplayers
                ,gameplayers
                ,playersCardsMap
                ,this.PLAYER_NAME
                ,deck
                ,lead
                ,rate
                ,bank
                ,rounds_done
        );
        showFirstFreeCards();
        checkIfShouldOpenNewCard();
        sitThePlayers();
    }
    @Override
    public void acceptMessageFromServerGameStarts(
            List<Player> allplayers
            ,List<Player> gameplayers
            ,List<Integer> deck
            ,Map<String,List<Integer>> playersCardsMap
            ,String lead
            ,Integer base_rate
    ) {
        gameStats.setGame(
                allplayers
                ,allplayers
                ,playersCardsMap
                ,this.PLAYER_NAME
                ,deck
                ,lead
                ,base_rate
                ,0
                ,0
        );
        showFirstFreeCards();
        sitThePlayers();
        serverController.sendMessageOnServerHandPower(gameStats.getMainPlayerHandPower(5));
    }

    private void showFirstFreeCards(){
        gameView.setCardView(
                ViewControllerActionCode.ADD_COMMUNITY_CARD_FIRST
                ,gameStats.getDeck().get(0)
        );
        gameView.setCardView(
                ViewControllerActionCode.ADD_COMMUNITY_CARD_SECOND
                ,gameStats.getDeck().get(1)
        );
        gameView.setCardView(
                ViewControllerActionCode.ADD_COMMUNITY_CARD_THIRD
                ,gameStats.getDeck().get(2)
        );
    }

    private void sitThePlayers(){
        this.gameStats.getPlayers().forEach((i) -> {
                    if (gameStats.checkIfPlaceIsNotTaken(ViewControllerActionCode.POSITION_OPPONENT_FIRST)) {
                        Logger.getAnonymousLogger().info("opponent first seat is empty. sitting the player "+i.getName());
                        i.setPos(ViewControllerActionCode.POSITION_OPPONENT_FIRST);
                    }if (gameStats.checkIfPlaceIsNotTaken(ViewControllerActionCode.POSITION_OPPONENT_SECOND))
                        i.setPos(ViewControllerActionCode.POSITION_OPPONENT_SECOND);
                    if (gameStats.checkIfPlaceIsNotTaken(ViewControllerActionCode.POSITION_OPPONENT_THIRD))
                        i.setPos(ViewControllerActionCode.POSITION_OPPONENT_THIRD);
                    if (gameStats.checkIfPlaceIsNotTaken(ViewControllerActionCode.POSITION_OPPONENT_FOURTH))
                        i.setPos(ViewControllerActionCode.POSITION_OPPONENT_FOURTH);
                }
        );
    }


    //короче, очень полезные метод
    //смотрит, изменился ли раунд с его последнего вызова
    //если да,открывает, то скольно надо

    //типа мы будем при каждом действии спрашивать себя
    // "а не надо ли мне открыть ещё одну community card?"
    private static int roundNum = 0;
    private void checkIfShouldOpenNewCard(){
        int roundNow = gameStats.getRoundsNum();
        if(roundNow > this.roundNum){
            switch (roundNow){
                case 1:
                    gameView.setCardView(
                    ViewControllerActionCode.ADD_COMMUNITY_CARD_FOURTH
                        ,gameStats.getDeck().get(3)
                    ); break;
                case 2:
                    gameView.setCardView(
                            ViewControllerActionCode.ADD_COMMUNITY_CARD_FIFTH
                            ,gameStats.getDeck().get(4)
                    ); break;
                default:
                    //у нас открыты все карты
            }
        }
        this.roundNum++;
        //если у нас баг, и вдруг не открытыми осталось
        //больше одной карты, от мы откроем ещё столько,
        //сколько нужно
        if(gameStats.getRoundsNum() > this.roundNum)
            checkButtonClicked();
    }

    //когда игра заканчивается, нужно увидеть карты
    //всех игроков, кто не слился
    private void showCardsWhenGameIsDone(){
        ArrayList<Player> playersInTheEnd = new ArrayList<>(gameStats.getPlayers());
        for(int i=0; i<playersInTheEnd.size(); i++){
            if(playersInTheEnd.get(i).isActive()){}
                //TODO:сделать так, чтобы показались карты всех активных игроков
                //switch ()
        }
    }
}