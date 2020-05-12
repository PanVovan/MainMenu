package com.poker.holdem;
/*
* Функционал будет дополнятся в зависимости от методов
*/


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
        void acceptMessageFromServerNewPlayerJoin();  //Там по ходу посмотрим что передается, как и везде
        void acceptMessageFromServerOpponentCheck(String name);
        void acceptMessageFromServerOpponentRaise(String name);
        void acceptMessageFromServerOpponentAllIn(String name);
        void acceptMessageFromServerOpponentFold(String name);
        void acceptMessageFromServerOpponentLeft(String name);
        void acceptMessageFromServerAddCommunityCard(int card);
        void acceptMessageFromServerAddCard(String playerName, int card);
    }


    interface Server{
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



}
