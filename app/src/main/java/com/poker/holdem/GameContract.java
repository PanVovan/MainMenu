package com.poker.holdem;
/*
* Функционал будет дополнятся в зависимости от методов
*/


import android.app.Activity;
import android.content.Intent;

import com.poker.holdem.logic.handlogic.combination.HandCombination;
import com.poker.holdem.logic.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GameContract {
    interface View {
        void setHandPowerProgressBarProgress(HandCombination combination);
        void showGameEventMessage(String message, int timeInMillis);

        void setLead(int pos);
        void setBank(int val);
        void showAllPlayersCards(HashMap<Integer, List<Integer> > cards);
        void setRate(int val);

        //установить значения
        void clearCards (int typeOfClear);
        void setCardView (int action, int card);

        //передавать игроками, на мой взгляд, лучше
        //так мы можем легко добавить много параметров
        //типа активности игрока
        void setOpponentView (int pos, Player player);
        void setPlayerView (Player player);
        void updatePlayerMoney(int pos, Integer money);
        void clearOpponentView(int pos);
        void clearAll();

        void showFirstOpponentEventMessage(String message, int timeInMillis);
        void showSecondOpponentEventMessage(String message, int timeInMillis);
        void showThirdOpponentEventMessage(String message, int timeInMillis);
        void showFourthOpponentEventMessage(String message, int timeInMillis);
    }

    interface Presenter{
        void onViewStopped();

        //для отображения во вью
        int getRate();
        int getPlayerMoney();

        //Обработчик нажатий кнопок
        void foldButtonClicked();
        void checkButtonClicked();
        void raiseButtonClicked(int rate);
        void allInButtonClicked();

        void exitButtonClicked();

        //Если чето с сервера приходит
        void acceptMessageFromServerNewPlayerJoin(Player player);  //Там по ходу посмотрим что передается, как и везде
        void acceptMessageFromServerOpponentCheck(String name, String nextLead, boolean didRoundChange);
        void acceptMessageFromServerOpponentRaise(String name, Integer rate, String nextLead, boolean didRoundChange);
        void acceptMessageFromServerOpponentAllIn(String name, String nextLead, boolean didRoundChange);
        void acceptMessageFromServerOpponentFold(String name, String nextLead, boolean didRoundChange);
        void acceptMessageFromServerOpponentLeft(String name, String nextLead, boolean didRoundChange);
        void acceptMessageFromServerOpponentMeDidSomething(String nextLead, boolean didRoundChange);
        void acceptMessageFromServerOpponentStop(String name);
        void acceptMessageFromServerOpponentRestore(String name);
        void acceptMessageFromServerEndGame(Integer winVal, List<String> winners);
        void acceptMessageFromServerEnterLobby(
                List<Player> allplayers
                ,List<Player> gameplayers
                ,List<Integer> deck
                ,Map<String, List<Integer>> playersCardsMap
                ,String lead
                ,Integer base_rate
                ,Integer rounds_done
                ,Integer bank
                ,boolean isgamerunning
        );
        void acceptMessageFromServerRestore(
                List<Player> allplayers
                ,List<Player> gameplayers
                ,List<Integer> deck
                ,Map<String, List<Integer>> playersCardsMap
                ,String lead
                ,Integer base_rate
                ,Integer rounds_done
                ,Integer bank
                ,boolean isgamerunning
        );
        void acceptMessageFromServerGameStarts(
                List<Player> allplayers
                ,List<Player> gameplayers
                ,List<Integer> deck
                ,Map<String, List<Integer>> playersCardsMap
                ,String lead
                ,Integer base_rate
        );

    }

    interface Server{
        void sendMessageOnServerFold();
        void sendMessageOnServerCheck();
        void sendMessageOnServerRaise(int rate);
        void sendMessageOnServerAllIn();
        void sendMessageOnServerLeave();
        void sendMessageOnServerStop();
        void sendMessageOnServerRestore();
        void sendMessageOnServerHandPower(long power);
        void sendMessageOnServerEnterLobby(String roomName);
        void disconnect();
    }
}
