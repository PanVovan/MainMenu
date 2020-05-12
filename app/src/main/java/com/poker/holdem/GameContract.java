package com.poker.holdem;
/*
* Функционал будет дополнятся в зависимости от методов
*/


import com.poker.holdem.logic.player.Player;

import java.util.List;
import java.util.Map;

public interface GameContract {
    interface View {
        //установить значения
        void clearCards (int typeOfClear);
        void setCardView (int action, int card);
        void setOpponentView (int pos, String name, Integer money, int picture);
        void setPlayerView (Integer money, int picture);

    }

    interface Presenter{
        //Обработчик нажатий кнопок
        void foldButtonClicked();
        void checkButtonClicked();
        void raiseButtonClicked(int rate);
        void exitButtonClicked();

        //Если чето с сервера приходит
        void acceptMessageFromServerNewPlayerJoin(Player player);  //Там по ходу посмотрим что передается, как и везде
        void acceptMessageFromServerOpponentCheck(String name, String nextLead);
        void acceptMessageFromServerOpponentRaise(String name, Integer rate, String nextLead);
        void acceptMessageFromServerOpponentAllIn(String name, String nextLead);
        void acceptMessageFromServerOpponentFold(String name, String nextLead);
        void acceptMessageFromServerOpponentLeft(String name, String nextLead);
        void acceptMessageFromServerOpponentStop(String name);
        void acceptMessageFromServerOpponentRestore(String name);
        void acceptMessageFromServerEndGame(Integer winVal, List<String> winners);
        void acceptMessageFromServerAddCommunityCard(int card);
        void acceptMessageFromServerAddCard(String playerName, int card);
        void acceptMessageFromServerEnterLobby(
                List<Player> allplayers
                ,List<Player> gameplayers
                ,List<Integer> deck
                ,Map<String, List<Integer>> playersCardsMap
                ,String lead
                ,Integer base_rate
        );
        void acceptMessageFromServerRestore(
                List<Player> allplayers
                ,List<Player> gameplayers
                ,List<Integer> deck
                ,Map<String, List<Integer>> playersCardsMap
                ,String lead
                ,Integer base_rate
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
    }



}
