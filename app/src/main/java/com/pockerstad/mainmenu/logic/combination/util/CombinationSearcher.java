package com.pockerstad.mainmenu.logic.combination.util;

import com.pockerstad.mainmenu.logic.card.Card;
import com.pockerstad.mainmenu.logic.combination.Combination;

import java.util.SortedSet;

public interface CombinationSearcher {
    Combination analyzeCards(SortedSet<Card> inputCards);
    Combination analyzeIntegers(SortedSet<Integer> inputCards);
}
