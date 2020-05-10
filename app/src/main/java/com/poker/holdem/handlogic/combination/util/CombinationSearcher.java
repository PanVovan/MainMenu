package com.poker.holdem.handlogic.combination.util;

import com.poker.holdem.handlogic.card.Card;
import com.poker.holdem.handlogic.combination.Combination;

import java.util.SortedSet;

public interface CombinationSearcher {
    Combination analyzeCards(SortedSet<Card> inputCards);
    Combination analyzeIntegers(SortedSet<Integer> inputCards);
}
