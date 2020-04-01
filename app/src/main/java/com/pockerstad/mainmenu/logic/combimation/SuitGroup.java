package com.pockerstad.mainmenu.logic.combimation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.annimon.stream.Stream;
import com.pockerstad.mainmenu.logic.card.Card;
import com.pockerstad.mainmenu.logic.card.Suit;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import com.annimon.stream.Collectors;

//Реализуем Iterable чтоб можно было использовать этот класс в foreach
public class SuitGroup implements Iterable<Map.Entry<Suit, List<Card>>> {

    private final Map<Suit, List<Card>> suitMap;


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


    private static Map<Suit, List<Card>> initSuitGroup(final SortedSet<Card> cards) {
        //Возвращаем мапу, сгруппированную по масти
        return Collections.unmodifiableMap(new TreeMap<>(Stream.of(cards).collect(Collectors.groupingBy(Card::getSuit))));
    }

}