package com.pockerstad.mainmenu.logic.card;

public enum Suit {
    DIAMONDS(1),
    CLUBS(2),
    HEARTS(3),
    SPADES(4);

    private int suit_val;

    Suit(int suit) {
        this.suit_val = suit;
    }

    public int getSuit_val() {
        return this.suit_val;
    }
}
