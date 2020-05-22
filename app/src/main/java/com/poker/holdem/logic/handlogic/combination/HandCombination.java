package com.poker.holdem.logic.handlogic.combination;

public enum HandCombination {
    NO_CARDS(0),
    HIGH_CARD(1),
    PAIR(2),
    TWO_PAIR(3),
    SET(4),
    WHEEL(5),
    STRAIGHT(6),
    FLUSH(7),
    FULL_HOUSE(8),
    FOUR_OF_A_KIND(9),
    STRAIGHT_FLUSH_WHEEL(10),
    STRAIGHT_FLUSH(11),
    ROYAL_FLUSH(12);

    private int value;

    HandCombination(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String toString(){
        switch (this.value){
            case 0:
                return "NO CARDS";
            case 1:
                return "HIGH CARD";
            case 2:
                return "PAIR";
            case 3:
                return "TWO PAIRS";
            case 4:
                return "SET";
            case 5:
                return "WHEEL";
            case 6:
                return "STRAIGHT";
            case 7:
                return "FLUSH";
            case 8:
                return "FULL HOUSE";
            case 9:
                return "FOUR OF A KIND";
            case 10:
                return "STRAIGHT FLUSH WHEEL";
            case 11:
                return "STRAIGHT FLUSH";
            case 12:
                return "ROYAL FLUSH";
            default:
                return "Undefined";
        }
    }
}
