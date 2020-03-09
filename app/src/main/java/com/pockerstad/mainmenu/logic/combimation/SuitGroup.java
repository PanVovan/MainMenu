package com.pockerstad.mainmenu.logic.combimation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pockerstad.mainmenu.logic.card.Card;
import com.pockerstad.mainmenu.logic.card.Suit;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SuitGroup implements Iterable<Map.Entry<Suit, List<Card>>> {

    private final Map<Suit, List<Card>> suitMap;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public SuitGroup(final SortedSet<Card> cards) {
        this.suitMap = initSuitGroup(cards);
    }

    Map<Suit, List<Card>> getSuitMap() {
        return this.suitMap;
    }

    @Override
    public Iterator<Map.Entry<Suit, List<Card>>> iterator() {
        return this.suitMap.entrySet().iterator();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static Map<Suit, List<Card>> initSuitGroup(final SortedSet<Card> cards) {
        return Collections.unmodifiableMap(new TreeMap<>(cards.stream().collect(Collectors.groupingBy(Card::getSuit))));
    }

}