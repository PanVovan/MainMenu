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

        //передавать игроками, на мой взгляд, лучше
        //так мы можем легко добавить много параметров
        //типа активности игрока
        void setOpponentView (int pos, Player player);
        void setPlayerView (Player player);
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
        void acceptMessageFromServerEnterLobby(
                List<Player> allplayers
                ,List<Player> gameplayers
                ,List<Integer> deck
                ,Map<String, List<Integer>> playersCardsMap
                ,String lead
                ,Integer base_rate
                ,Integer rounds_done
        );
        void acceptMessageFromServerRestore(
                List<Player> allplayers
                ,List<Player> gameplayers
                ,List<Integer> deck
                ,Map<String, List<Integer>> playersCardsMap
                ,String lead
                ,Integer base_rate
                ,Integer rounds_done
        );
        void acceptMessageFromServerGameStarts(
                List<Player> allplayers
                ,List<Player> gameplayers
                ,List<Integer> deck
                ,Map<String, List<Integer>> playersCardsMap
                ,String lead
                ,Integer base_rate
                ,Integer rounds_done
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
