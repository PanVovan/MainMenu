package com.poker.holdem.view.util;

public class ViewControllerActionCode {

    //Ничего не делать
    public static final int NONE = 0;

    //константы для добавления карт игрока
    public static final int ADD_FIRST_PLAYER_CARD   = 1;
    public static final int ADD_SECOND_PLAYER_CARD  = 2;

    //константы для добавления карт первого оппонента
    public static final int ADD_FIRST_OPPONENT_FIRST_CARD   = 11;
    public static final int ADD_FIRST_OPPONENT_SECOND_CARD  = 12;

    //константы для добавления карт второго оппонента
    public static final int ADD_SECOND_OPPONENT_FIRST_CARD      = 21;
    public static final int ADD_SECOND_OPPONENT_SECOND_CARD     = 22;

    //константы для добавления карт второго оппонента
    public static final int ADD_THIRD_OPPONENT_FIRST_CARD   = 31;
    public static final int ADD_THIRD_OPPONENT_SECOND_CARD  = 32;

    //константы для добавления карт второго оппонента
    public static final int ADD_FOURTH_OPPONENT_FIRST_CARD      = 41;
    public static final int ADD_FOURTH_OPPONENT_SECOND_CARD     = 42;

    public static final int ADD_FIRST_OPPONENT_INVISIBLE_FIRST_CARD     = 51;
    public static final int ADD_FIRST_OPPONENT_INVISIBLE_SECOND_CARD    = 52;

    //константы для добавления карт второго оппонента
    public static final int ADD_SECOND_OPPONENT_INVISIBLE_FIRST_CARD    = 61;
    public static final int ADD_SECOND_OPPONENT_INVISIBLE_SECOND_CARD   = 62;

    //константы для добавления карт второго оппонента
    public static final int ADD_THIRD_OPPONENT_INVISIBLE_FIRST_CARD     = 71;
    public static final int ADD_THIRD_OPPONENT_INVISIBLE_SECOND_CARD    = 72;

    //константы для добавления карт второго оппонента
    public static final int ADD_FOURTH_OPPONENT_INVISIBLE_FIRST_CARD    = 81;
    public static final int ADD_FOURTH_OPPONENT_INVISIBLE_SECOND_CARD   = 82;


    //Константы для добавления общих карт
    public static final int ADD_COMMUNITY_CARD_FIRST        = 101;
    public static final int ADD_COMMUNITY_CARD_SECOND       = 102;
    public static final int ADD_COMMUNITY_CARD_THIRD        = 103;
    public static final int ADD_COMMUNITY_CARD_FOURTH       = 104;
    public static final int ADD_COMMUNITY_CARD_FIFTH        = 105;


    public static final int CLEAR_PLAYER_CARDS              = 200;
    public static final int CLEAR_FIRST_OPPONENT_CARDS      = 201;
    public static final int CLEAR_SECOND_OPPONENT_CARDS     = 202;
    public static final int CLEAR_THIRD_OPPONENT_CARDS      = 203;
    public static final int CLEAR_FOURTH_OPPONENT_CARDS     = 204;
    public static final int CLEAR_COMMUNITY_CARDS           = 205;
    public static final int CLEAR_ALL_CARDS                 = 210;

    public static final int ADD_FIRST_OPPONENT      = 401 ;
    public static final int ADD_SECOND_OPPONENT     = 402 ;
    public static final int ADD_THIRD_OPPONENT      = 403 ;
    public static final int ADD_FOURTH_OPPONENT     = 404 ;

    public static final int SET_FIRST_OPPONENT_MONEY      = 501 ;
    public static final int SET_SECOND_OPPONENT_MONEY     = 502 ;
    public static final int SET_THIRD_OPPONENT_MONEY      = 503 ;
    public static final int SET_FOURTH_OPPONENT_MONEY     = 504 ;
    public static final int SET_MAIN_PLAYER_MONEY         = 505 ;

    public static final int POSITION_OPPONENT_FIRST     = 601;
    public static final int POSITION_OPPONENT_SECOND    = 602;
    public static final int POSITION_OPPONENT_THIRD     = 603;
    public static final int POSITION_OPPONENT_FOURTH    = 604;
    public static final int POSITION_MAIN_PLAYER        = 605;

    public static int getPosition(int pos){
        switch (pos){
            case 1:

        }
        return -1;
    }


}
