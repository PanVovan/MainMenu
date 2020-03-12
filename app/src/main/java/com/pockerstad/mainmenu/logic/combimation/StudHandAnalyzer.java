package com.pockerstad.mainmenu.logic.combimation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pockerstad.mainmenu.logic.card.Card;
import com.pockerstad.mainmenu.logic.combimation.util.HandAnalyzer;

import java.util.SortedSet;


public class StudHandAnalyzer implements HandAnalyzer {

    private final SortedSet<Card> yourCards;
    private final Classification handClassification;
    private final RankGroup rankGroup;
    private final SuitGroup suitGroup;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public StudHandAnalyzer(SortedSet<Card> yourCards) {
        this.yourCards = yourCards;
        this.rankGroup = new RankGroup(this.yourCards);
        this.suitGroup = new SuitGroup(this.yourCards);
        this.handClassification = PokerHandUtils.classifyPokerHand(getRankGroup(), getSuitGroup(), getCards());
    }

    @Override
    public Classification getClassification() {
        return this.handClassification;
    }

    @Override
    public RankGroup getRankGroup() {
        return this.rankGroup;
    }

    @Override
    public SuitGroup getSuitGroup() {
        return this.suitGroup;
    }

    @Override
    public SortedSet<Card> getCards() {
        return this.yourCards;
    }
}
