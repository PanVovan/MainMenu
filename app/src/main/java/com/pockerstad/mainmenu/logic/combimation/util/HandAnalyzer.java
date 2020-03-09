package com.pockerstad.mainmenu.logic.combimation.util;

import com.pockerstad.mainmenu.logic.card.Card;
import com.pockerstad.mainmenu.logic.combimation.Classification;
import com.pockerstad.mainmenu.logic.combimation.RankGroup;
import com.pockerstad.mainmenu.logic.combimation.SuitGroup;

import java.util.SortedSet;

public interface HandAnalyzer {
    SortedSet<Card> getCards();

    Classification getClassification();

    RankGroup getRankGroup();

    SuitGroup getSuitGroup();
}
