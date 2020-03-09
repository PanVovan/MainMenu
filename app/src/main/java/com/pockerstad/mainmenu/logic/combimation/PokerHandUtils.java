package com.pockerstad.mainmenu.logic.combimation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pockerstad.mainmenu.logic.card.Card;
import com.pockerstad.mainmenu.logic.card.Rank;
import com.pockerstad.mainmenu.logic.card.Suit;
import com.pockerstad.mainmenu.logic.combimation.util.Hand;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import static com.pockerstad.mainmenu.logic.card.Rank.*;

public enum PokerHandUtils {
    ;   //no instance
    public static final int TIE = 0;


    //листы роял-флешей и колес
    public static final List<Card> ROYAL_FLUSH_SPADES = Arrays.asList(new Card(ACE, Suit.SPADES),
            new Card(Rank.KING, Suit.SPADES),
            new Card(Rank.QUEEN, Suit.SPADES),
            new Card(Rank.JACK, Suit.SPADES),
            new Card(Rank.TEN, Suit.SPADES));


    public static final List<Card> ROYAL_FLUSH_HEARTS = Arrays.asList(new Card(ACE, Suit.HEARTS),
            new Card(Rank.KING, Suit.HEARTS),
            new Card(Rank.QUEEN, Suit.HEARTS),
            new Card(Rank.JACK, Suit.HEARTS),
            new Card(Rank.TEN, Suit.HEARTS));

    public static final List<Card> ROYAL_FLUSH_CLUBS = Arrays.asList(new Card(ACE, Suit.CLUBS),
            new Card(Rank.KING, Suit.CLUBS),
            new Card(Rank.QUEEN, Suit.CLUBS),
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.TEN, Suit.CLUBS));

    public static final List<Card> ROYAL_FLUSH_DIAMONDS = Arrays.asList(new Card(ACE, Suit.DIAMONDS),
            new Card(Rank.KING, Suit.DIAMONDS),
            new Card(Rank.QUEEN, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.DIAMONDS),
            new Card(Rank.TEN, Suit.DIAMONDS));

    public static final List<Card> STRAIGHT_WHEEL_SPADES = Arrays.asList(new Card(ACE, Suit.SPADES),
            new Card(Rank.TWO, Suit.SPADES),
            new Card(THREE, Suit.SPADES),
            new Card(FOUR, Suit.SPADES),
            new Card(FIVE, Suit.SPADES));

    public static final List<Card> STRAIGHT_WHEEL_HEARTS = Arrays.asList(new Card(ACE, Suit.HEARTS),
            new Card(Rank.TWO, Suit.HEARTS),
            new Card(THREE, Suit.HEARTS),
            new Card(FOUR, Suit.HEARTS),
            new Card(FIVE, Suit.HEARTS));

    public static final List<Card> STRAIGHT_WHEEL_CLUBS = Arrays.asList(new Card(ACE, Suit.CLUBS),
            new Card(Rank.TWO, Suit.CLUBS),
            new Card(THREE, Suit.CLUBS),
            new Card(FOUR, Suit.CLUBS),
            new Card(FIVE, Suit.CLUBS));

    public static final List<Card> STRAIGHT_WHEEL_DIAMONDS = Arrays.asList(new Card(ACE, Suit.DIAMONDS),
            new Card(Rank.TWO, Suit.DIAMONDS),
            new Card(THREE, Suit.DIAMONDS),
            new Card(FOUR, Suit.DIAMONDS),
            new Card(FIVE, Suit.DIAMONDS));


    // Листы рангов флешей
    public static final List<Rank> STRAIGHT_TWO_TO_SIX = Arrays.asList(TWO, THREE, FOUR, FIVE, SIX);

    public static final List<Rank> STRAIGHT_THREE_TO_SEVEN = Arrays.asList(THREE, FOUR, FIVE, SIX, SEVEN);

    public static final List<Rank> STRAIGHT_FOUR_TO_EIGHT = Arrays.asList(FOUR, FIVE, SIX, SEVEN, EIGHT);

    public static final List<Rank> STRAIGHT_FIVE_TO_NINE = Arrays.asList(FIVE, SIX, SEVEN, EIGHT, NINE);

    public static final List<Rank> STRAIGHT_SIX_TO_TEN = Arrays.asList(SIX, SEVEN, EIGHT, NINE, TEN);

    public static final List<Rank> STRAIGHT_SEVEN_TO_JACK = Arrays.asList(SEVEN, EIGHT, NINE, TEN, JACK);

    public static final List<Rank> STRAIGHT_EIGHT_TO_QUEEN = Arrays.asList(EIGHT, NINE, TEN, JACK, QUEEN);

    public static final List<Rank> STRAIGHT_NINE_TO_KING = Arrays.asList(NINE, TEN, JACK, QUEEN, KING);

    public static final List<Rank> STRAIGHT_TEN_TO_ACE = Arrays.asList(TEN, JACK, QUEEN, KING, ACE);

    public static void checkHandClassification(final Hand hand,
                                               final ClassificationRank classificationRank) {
        if(hand.getHandAnalyzer().getClassification().getClassificationRank() != classificationRank) {
            throw new RuntimeException("Hand : " +hand+ " does not match expected classificationRank " + classificationRank);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Classification classifyPokerHand(final RankGroup rankGroup,
                                                   final SuitGroup suitGroup,
                                                   final SortedSet<Card> cards) {
        final PokerHandClassifier handDetector = new PokerHandClassifier(rankGroup, suitGroup, cards);
        return handDetector.classify();
    }
}