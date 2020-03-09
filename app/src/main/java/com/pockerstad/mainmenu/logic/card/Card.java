package com.pockerstad.mainmenu.logic.card;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Card implements Comparable<Card>{

    private final Rank rank;
    private final Suit suit;

    private final static Map<String, Card> CARD_CACHE = initCache();

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    //Создание карт

    private static Map<String, Card> initCache() {
        //Создаем ссылку типа мап карт и присваеваем ей объект типа хэшмап
        final Map<String, Card> deck = new HashMap<>();
        //Заполняем мапу в соответствии с ее мастью и рангом
        for (final Suit suit : Suit.values()) {
            for (final Rank rank : Rank.values()) {
                deck.put(cardKey(rank, suit), new Card(rank, suit));
            }
        }
        return Collections.unmodifiableMap(deck);
    }

    //Статический метод получения карты
    static Card getCard(final Rank rank,
                        final Suit suit) {
        final Card card = CARD_CACHE.get(cardKey(rank, suit));
        if (card != null) {
            return card;
        }
        throw new RuntimeException("Invalid card ! " + rank + " " + suit);
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    private static String cardKey(final Rank rank,
                                  final Suit suit) {
        return rank + " of " + suit;
    }

    //Метод для сравнения
    @Override
    public int compareTo(final Card o) {
        final int rankComparison = Integer.compare(this.rank.getRankValue(), o.rank.getRankValue());
        return rankComparison != 0 ? rankComparison : Integer.compare(this.suit.getSuitValue(), o.suit.getSuitValue());
    }

    //Переопределяем (для удобства) методы Object
    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return this.rank == card.rank && this.suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = this.rank != null ? this.rank.hashCode() : 0;
        result = 31 * result + (this.suit != null ? this.suit.hashCode() : 0);
        return result;
    }
}
