package com.poker.holdem.handlogic.combination;

import com.poker.holdem.handlogic.card.Card;

import java.util.Collections;
import java.util.SortedSet;

public class Combination {
    private HandCombination combinationRank;
    private SortedSet<Card> combination_cards;

    //комбинация - цель поиска всего анализатора
    public Combination (HandCombination r, SortedSet<Card> cards){
        this.combinationRank = r;
        this.combination_cards = Collections.unmodifiableSortedSet(cards);
    }

    //если карт нет, создаётся это
    public Combination(){
        this.combinationRank = HandCombination.NO_CARDS;
        this.combination_cards = null;
    }

    public HandCombination getCombinationRank() {
        return this.combinationRank;
    }

    public SortedSet<Card> getCombination_cards() {
        return this.combination_cards;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder= new StringBuilder(this.combinationRank.getValue() + " ");
        this.combination_cards.stream().forEach(card -> stringBuilder.append(card.getCode().toString() + " "));
        return stringBuilder.toString();
    }
}
