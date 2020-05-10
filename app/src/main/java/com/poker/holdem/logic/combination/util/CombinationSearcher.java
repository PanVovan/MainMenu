package com.poker.holdem.logic.combination.util;

import com.poker.holdem.logic.card.Card;
import com.poker.holdem.logic.combination.Combination;

import java.util.SortedSet;

public interface CombinationSearcher {
    Combination analyzeCards(SortedSet<Card> inputCards);
    Combination analyzeIntegers(SortedSet<Integer> inputCards);
}
