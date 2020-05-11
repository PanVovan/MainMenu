package com.poker.holdem;

import com.poker.holdem.players.utils.Player;

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

        //Передача сообщений от сервера во View
        void setCard(int action, int card);
        void clearCards(int typeOfClear);
        void setPlayer();
        void setOpponent();
    }

    interface Logic {
        //Получить данные из логики
        int getCard();
        Player getPlayer();


        //отправить данные на сервер
        void sendMessageOnServerFold();
        void sendMessageOnServerCheck();
        void sendMessageOnServerRaise(int rate);
        void sendMessageOnServerAllIn();
        void sendMessageOnServerLeave();
        void sendMessageOnServerStop();
        void sendMessageOnServerRestore();
        void sendMessageOnServerHandPower();
        void sendMessageOnServerEnterLobby();
    }

    interface MenuLobbies{
        void sendMessageOnServerGetLobbies();
    }

    interface Registrator{
        void sendMessageOnServerRegister();
        void sendMessageOnServerAuth();
    }
}
