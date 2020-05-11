package com.poker.holdem.logic.handlogic.combination.util;

import com.poker.holdem.logic.handlogic.card.Card;
import com.poker.holdem.logic.handlogic.combination.Combination;

import java.util.SortedSet;

public interface CombinationSearcher {
    Combination analyzeCards(SortedSet<Card> inputCards);
    Combination analyzeIntegers(SortedSet<Integer> inputCards);
}
