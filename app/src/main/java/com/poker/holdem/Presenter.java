package com.poker.holdem;

import android.content.Context;

import com.poker.holdem.constants.Constants;
import com.poker.holdem.logic.handlogic.Hand;
import com.poker.holdem.logic.handlogic.card.Card;
import com.poker.holdem.logic.player.Player;
import com.poker.holdem.view.util.ViewControllerActionCode;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Presenter implements GameContract.Presenter {

    private GameContract.View gameView;
    private GameContract.Server serverController;

    private Hand.Builder handBuilder;



    public Presenter(GameContract.View view){
        this.handBuilder = new Hand.Builder();
        this.serverController = new ServerController(this) ;
        this.gameView = view;
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
    public void acceptMessageFromServerOpponentCheck(String name, String nextLead) {

    }

    @Override
    public void acceptMessageFromServerOpponentRaise(String name, Integer rate, String nextLead) {

    }

    @Override
    public void acceptMessageFromServerOpponentAllIn(String name, String nextLead) {

    }

    @Override
    public void acceptMessageFromServerOpponentFold(String name, String nextLead) {

    }

    @Override
    public void acceptMessageFromServerOpponentLeft(String name, String nextLead) {

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
    @Override
    public void acceptMessageFromServerAddCommunityCard(int card) {
        handBuilder.addCommunityCard(Optional.of(new Card(card)));
        switch (handBuilder.getCommunityCards().size()){
            case 1:
                gameView.setCardView(ViewControllerActionCode.ADD_COMMUNITY_CARD_FIRST, card);
                break;
            case 2:
                gameView.setCardView(ViewControllerActionCode.ADD_COMMUNITY_CARD_SECOND, card);
                break;
            case 3:
                gameView.setCardView(ViewControllerActionCode.ADD_COMMUNITY_CARD_THIRD, card);
                break;
            case 4:
                gameView.setCardView(ViewControllerActionCode.ADD_COMMUNITY_CARD_FOURTH, card);
                break;
            case 5:
                gameView.setCardView(ViewControllerActionCode.ADD_COMMUNITY_CARD_FIFTH, card);
                break;
        }
    }


    //TODO: извлечь из префов имя игрока, или как что то еще в зависимость от сервера
    //имя извлечено
    @Override
    public void acceptMessageFromServerAddCard(String name, int card) {
        String playername = PokerApplicationManager
                .getInstance()
                .getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
                .getString(Constants.PLAYER_NAME, "");
        if (name.equals(playername)){
            handBuilder.addHoleCard(Optional.of(new Card(card)));
            //Тут мы смотрим лист кард, и в зависимости от его заполненности генерируем комманду для вью
            gameView.setCardView(ViewControllerActionCode.ADD_FIRST_PLAYER_CARD, card);
            gameView.setCardView(ViewControllerActionCode.ADD_SECOND_PLAYER_CARD, card);
        }
        //Тут мы проходим массив игрока
    }

    @Override
    public void acceptMessageFromServerEnterLobby(List<Player> allplayers, List<Player> gameplayers, List<Integer> deck, Map<String, List<Integer>> playersCardsMap, String lead, Integer base_rate) {

    }

    @Override
    public void acceptMessageFromServerRestore(List<Player> allplayers, List<Player> gameplayers, List<Integer> deck, Map<String, List<Integer>> playersCardsMap, String lead, Integer base_rate) {

    }

    @Override
    public void acceptMessageFromServerGameStarts(List<Player> allplayers, List<Player> gameplayers, List<Integer> deck, Map<String, List<Integer>> playersCardsMap, String lead, Integer base_rate) {

    }

}
