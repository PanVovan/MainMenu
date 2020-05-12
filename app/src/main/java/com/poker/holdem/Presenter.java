package com.poker.holdem;

import android.content.Context;

import com.poker.holdem.constants.Constants;
import com.poker.holdem.logic.handlogic.Hand;
import com.poker.holdem.logic.handlogic.card.Card;
import com.poker.holdem.logic.player.Player;
import com.poker.holdem.view.util.ViewControllerActionCode;

import java.util.List;
import java.util.Optional;

public class Presenter implements GameContract.Presenter {

    private GameContract.View gameView;
    private GameContract.Server serverController;

    private Hand.Builder handBuilder;
    private Player player;

    private List<Player> players;



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
    public void acceptMessageFromServerOpponentCheck(String name) {

    }

    @Override
    public void acceptMessageFromServerOpponentRaise(String name, Integer rate) {

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
                    players.get(i).getCards().add(card);
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
        }
    }

    @Override
    public void acceptMessageFromServerEnterLobby(List<Player> players, List<Integer> deck, String lead, Integer base_rate) {

    }

    @Override
    public void acceptMessageFromServerEnterLobby(List<Player> players, List<Integer> deck, String lead, Integer base_rate) {

    }
}
