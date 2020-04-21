package com.pockerstad.mainmenu.logic.combination;


import com.pockerstad.mainmenu.logic.card.Card;
import com.pockerstad.mainmenu.logic.card.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public enum HandUtils {;

    //листы роял флешей
    public static List<Card> ROYAL_FLUSH_SPADES = new ArrayList<>();
    static {
        ROYAL_FLUSH_SPADES.add(new Card(414));
        ROYAL_FLUSH_SPADES.add(new Card(413));
        ROYAL_FLUSH_SPADES.add(new Card(412));
        ROYAL_FLUSH_SPADES.add(new Card(411));
        ROYAL_FLUSH_SPADES.add(new Card(410));
    }
    public static List<Card> ROYAL_FLUSH_HEARTS = new ArrayList<>();
    static {
        ROYAL_FLUSH_HEARTS.add(new Card(314));
        ROYAL_FLUSH_HEARTS.add(new Card(313));
        ROYAL_FLUSH_HEARTS.add(new Card(312));
        ROYAL_FLUSH_HEARTS.add(new Card(311));
        ROYAL_FLUSH_HEARTS.add(new Card(310));
    }
    public static List<Card> ROYAL_FLUSH_CLUBS = new ArrayList<>();
    static {
        ROYAL_FLUSH_CLUBS.add(new Card(214));
        ROYAL_FLUSH_CLUBS.add(new Card(213));
        ROYAL_FLUSH_CLUBS.add(new Card(212));
        ROYAL_FLUSH_CLUBS.add(new Card(211));
        ROYAL_FLUSH_CLUBS.add(new Card(210));
    }
    public static List<Card> ROYAL_FLUSH_DIAMONDS = new ArrayList<>();
    static {
        ROYAL_FLUSH_DIAMONDS.add(new Card(114));
        ROYAL_FLUSH_DIAMONDS.add(new Card(113));
        ROYAL_FLUSH_DIAMONDS.add(new Card(112));
        ROYAL_FLUSH_DIAMONDS.add(new Card(111));
        ROYAL_FLUSH_DIAMONDS.add(new Card(110));
    }

    //ищем колёса стрит-флеш
    public static List<Card> STRAIGHT_FLUSH_SPADES_WHEEL = new ArrayList<>();
    static {
        STRAIGHT_FLUSH_SPADES_WHEEL.add(new Card(414));
        STRAIGHT_FLUSH_SPADES_WHEEL.add(new Card(402));
        STRAIGHT_FLUSH_SPADES_WHEEL.add(new Card(403));
        STRAIGHT_FLUSH_SPADES_WHEEL.add(new Card(404));
        STRAIGHT_FLUSH_SPADES_WHEEL.add(new Card(405));
    }
    public static List<Card> STRAIGHT_FLUSH_HEARTS_WHEEL = new ArrayList<>();
    static {
        STRAIGHT_FLUSH_HEARTS_WHEEL.add(new Card(314));
        STRAIGHT_FLUSH_HEARTS_WHEEL.add(new Card(302));
        STRAIGHT_FLUSH_HEARTS_WHEEL.add(new Card(303));
        STRAIGHT_FLUSH_HEARTS_WHEEL.add(new Card(304));
        STRAIGHT_FLUSH_HEARTS_WHEEL.add(new Card(305));
    }
    public static List<Card> STRAIGHT_FLUSH_CLUBS_WHEEL = new ArrayList<>();
    static {
        STRAIGHT_FLUSH_CLUBS_WHEEL.add(new Card(214));
        STRAIGHT_FLUSH_CLUBS_WHEEL.add(new Card(202));
        STRAIGHT_FLUSH_CLUBS_WHEEL.add(new Card(203));
        STRAIGHT_FLUSH_CLUBS_WHEEL.add(new Card(204));
        STRAIGHT_FLUSH_CLUBS_WHEEL.add(new Card(205));
    }
    public static List<Card> STRAIGHT_FLUSH_DIAMONDS_WHEEL = new ArrayList<>();
    static {
        STRAIGHT_FLUSH_DIAMONDS_WHEEL.add(new Card(114));
        STRAIGHT_FLUSH_DIAMONDS_WHEEL.add(new Card(102));
        STRAIGHT_FLUSH_DIAMONDS_WHEEL.add(new Card(103));
        STRAIGHT_FLUSH_DIAMONDS_WHEEL.add(new Card(104));
        STRAIGHT_FLUSH_DIAMONDS_WHEEL.add(new Card(105));
    }

    //ищем колёса просто стрит
    public static List<Rank> WHEEL = Arrays.asList(Rank.FIVE, Rank.FOUR, Rank.THREE, Rank.TWO, Rank.ACE);

    public static List<Rank> STRAIGHT_ACE = new ArrayList<>();
    static {
        STRAIGHT_ACE.add(Rank.TEN);
        STRAIGHT_ACE.add(Rank.JACK);
        STRAIGHT_ACE.add(Rank.QUEEN);
        STRAIGHT_ACE.add(Rank.KING);
        STRAIGHT_ACE.add(Rank.ACE);
    }
    public static List<Rank> STRAIGHT_KING = new ArrayList<>();
    static {
        STRAIGHT_KING.add(Rank.NINE);
        STRAIGHT_KING.add(Rank.TEN);
        STRAIGHT_KING.add(Rank.JACK);
        STRAIGHT_KING.add(Rank.QUEEN);
        STRAIGHT_KING.add(Rank.KING);
    }
    public static List<Rank> STRAIGHT_QUEEN = new ArrayList<>();
    static {
        STRAIGHT_QUEEN.add(Rank.EIGHT);
        STRAIGHT_QUEEN.add(Rank.NINE);
        STRAIGHT_QUEEN.add(Rank.TEN);
        STRAIGHT_QUEEN.add(Rank.JACK);
        STRAIGHT_QUEEN.add(Rank.QUEEN);
    }
    public static List<Rank> STRAIGHT_JACK = new ArrayList<>();
    static {
        STRAIGHT_JACK.add(Rank.SEVEN);
        STRAIGHT_JACK.add(Rank.EIGHT);
        STRAIGHT_JACK.add(Rank.NINE);
        STRAIGHT_JACK.add(Rank.TEN);
        STRAIGHT_JACK.add(Rank.JACK);
    }
    public static List<Rank> STRAIGHT_TEN = new ArrayList<>();
    static {
        STRAIGHT_TEN.add(Rank.SIX);
        STRAIGHT_TEN.add(Rank.SEVEN);
        STRAIGHT_TEN.add(Rank.EIGHT);
        STRAIGHT_TEN.add(Rank.NINE);
        STRAIGHT_TEN.add(Rank.TEN);
    }
    public static List<Rank> STRAIGHT_NINE = new ArrayList<>();
    static {
        STRAIGHT_NINE.add(Rank.FIVE);
        STRAIGHT_NINE.add(Rank.SIX);
        STRAIGHT_NINE.add(Rank.SEVEN);
        STRAIGHT_NINE.add(Rank.EIGHT);
        STRAIGHT_NINE.add(Rank.NINE);
    }
    public static List<Rank> STRAIGHT_EIGHT = new ArrayList<>();
    static {
        STRAIGHT_EIGHT.add(Rank.FOUR);
        STRAIGHT_EIGHT.add(Rank.FIVE);
        STRAIGHT_EIGHT.add(Rank.SIX);
        STRAIGHT_EIGHT.add(Rank.SEVEN);
        STRAIGHT_EIGHT.add(Rank.EIGHT);
    }
    public static List<Rank> STRAIGHT_SEVEN = new ArrayList<>();
    static {
        STRAIGHT_SEVEN.add(Rank.THREE);
        STRAIGHT_SEVEN.add(Rank.FOUR);
        STRAIGHT_SEVEN.add(Rank.FIVE);
        STRAIGHT_SEVEN.add(Rank.SIX);
        STRAIGHT_SEVEN.add(Rank.SEVEN);
    }
    public static List<Rank> STRAIGHT_SIX = new ArrayList<>();
    static {
        STRAIGHT_SIX.add(Rank.TWO);
        STRAIGHT_SIX.add(Rank.THREE);
        STRAIGHT_SIX.add(Rank.FOUR);
        STRAIGHT_SIX.add(Rank.FIVE);
        STRAIGHT_SIX.add(Rank.SIX);
    }


}
