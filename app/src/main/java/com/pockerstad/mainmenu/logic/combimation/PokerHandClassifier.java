package com.pockerstad.mainmenu.logic.combimation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pockerstad.mainmenu.logic.card.Card;
import com.pockerstad.mainmenu.logic.card.Rank;
import com.pockerstad.mainmenu.logic.card.Suit;
import com.pockerstad.mainmenu.logic.combimation.util.HandClassifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PokerHandClassifier implements HandClassifier {

    private final RankGroup rankGroup;
    private final SuitGroup suitGroup;
    private final SortedSet<Card> cards;

    PokerHandClassifier(final RankGroup rankGroup,
                        final SuitGroup suitGroup,
                        final SortedSet<Card> cards) {
        this.rankGroup = rankGroup;
        this.suitGroup = suitGroup;
        this.cards = Collections.unmodifiableSortedSet(cards);
    }

    //Поиск самой большой карты
    @RequiresApi(api = Build.VERSION_CODES.N)
    private SortedSet<Card> calculateHighCards() {
        return new TreeSet<>(this.cards.stream().limit(5).collect(Collectors.toSet()));
    }

    //Обнаружить классификацию карт
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Classification classify() {
        final Classification result = detectImpl();
        validateCards(result.getClassifiedCards());
        return result;
    }


    /////////////////////////////////ЛОГИКА НА КЛИЕНТЕ/////////////////////////////////////
    ///////////////////////////ПОИСК КЛАССИФИКАЦИИ КОМБИНАЦИИ//////////////////////////////


    //Метод обнаружения классификации
    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification detectImpl() {
        //Пытаемся обнаружить РоялФлеш
        return detectRoyalFlush();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification detectRoyalFlush() {
        final List<Card> handCards = new ArrayList<>(this.cards);

        //Если рука включает в себя роял флеш какого либо вида, то
        if (handCards.containsAll(PokerHandUtils.ROYAL_FLUSH_SPADES)) {
            //Удаляем элементы, не принадлежащие переданной коллекции
            handCards.retainAll(PokerHandUtils.ROYAL_FLUSH_SPADES);
            //и возвращаем новую классификацию
            return new Classification(ClassificationRank.ROYAL_FLUSH, new TreeSet<>(handCards));
        } else if (handCards.containsAll(PokerHandUtils.ROYAL_FLUSH_HEARTS)) {
            handCards.retainAll(PokerHandUtils.ROYAL_FLUSH_HEARTS);
            return new Classification(ClassificationRank.ROYAL_FLUSH, new TreeSet<>(handCards));
        } else if (handCards.containsAll(PokerHandUtils.ROYAL_FLUSH_CLUBS)) {
            handCards.retainAll(PokerHandUtils.ROYAL_FLUSH_CLUBS);
            return new Classification(ClassificationRank.ROYAL_FLUSH, new TreeSet<>(handCards));
        } else if (handCards.containsAll(PokerHandUtils.ROYAL_FLUSH_DIAMONDS)) {
            handCards.retainAll(PokerHandUtils.ROYAL_FLUSH_DIAMONDS);
            return new Classification(ClassificationRank.ROYAL_FLUSH, new TreeSet<>(handCards));
        }
        //Если не нашли РоялФлеш, ищем колеса
        return detectStraightFlushWheel();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification detectStraightFlushWheel() {
        final List<Card> handCards = new ArrayList<>(this.cards);

        //Если рука включает в себя колесо какого либо вида, то
        if (handCards.containsAll(PokerHandUtils.STRAIGHT_WHEEL_SPADES)) {
            //Удаляем элементы, не принадлежащие переданной коллекции
            handCards.retainAll(PokerHandUtils.STRAIGHT_WHEEL_SPADES);
            //и возвращаем новую классификацию
            return new Classification(ClassificationRank.STRAIGHT_FLUSH_WHEEL, new TreeSet<>(handCards));
        } else if (handCards.containsAll(PokerHandUtils.STRAIGHT_WHEEL_HEARTS)) {
            handCards.retainAll(PokerHandUtils.STRAIGHT_WHEEL_HEARTS);
            return new Classification(ClassificationRank.STRAIGHT_FLUSH_WHEEL, new TreeSet<>(handCards));
        } else if (handCards.containsAll(PokerHandUtils.STRAIGHT_WHEEL_CLUBS)) {
            handCards.retainAll(PokerHandUtils.STRAIGHT_WHEEL_CLUBS);
            return new Classification(ClassificationRank.STRAIGHT_FLUSH_WHEEL, new TreeSet<>(handCards));
        } else if (handCards.containsAll(PokerHandUtils.STRAIGHT_WHEEL_DIAMONDS)) {
            handCards.retainAll(PokerHandUtils.STRAIGHT_WHEEL_DIAMONDS);
            return new Classification(ClassificationRank.STRAIGHT_FLUSH_WHEEL, new TreeSet<>(handCards));
        }
        //Если не нашли колеса, то ищем флеш
        return detectStraightFlush();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification detectStraightFlush() {
        //Создаем мапу карт сгруппированных по мастям
        final Map<Suit, List<Card>> suitGroup = this.suitGroup.getSuitMap();
        for (final Map.Entry<Suit, List<Card>> entry : suitGroup.entrySet()) {
            if (entry.getValue().size() == 5) {
                //Конвертируем мапу в массив (для удобства)
                final Card[] cardArray = entry.getValue().toArray(new Card[entry.getValue().size()]);
                for (int i = 0; i < cardArray.length - 1; i++) {
                    if (cardArray[i].getRank().getRankValue() != cardArray[i + 1].getRank().getRankValue() - 1) {
                        //Если последующая карта в колекции не соответсвует комбинации флеш, ищем четыре одинаковых
                        return detectFourOfAKind();
                    }
                }
                return new Classification(ClassificationRank.STRAIGHT_FLUSH, new TreeSet<>(entry.getValue()));
            }
        }
        //Не нашли Флеш, ищем четыре одинаковых
        return detectFourOfAKind();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification detectFourOfAKind() {
        //Если в руке есть четвертки, то
        if (this.rankGroup.getQuadCount() == 1) {
            final Iterator<Map.Entry<Rank, List<Card>>> rankGroup = this.rankGroup.iterator();
            final SortedSet<Card> cards = new TreeSet<>();
            //Добавляем все карты соответствующие условию в коллекцию и также добавляем кикер
            cards.addAll(rankGroup.next().getValue());
            cards.add(extractQuadKicker(rankGroup));
            //Возвращаем классификацию
            return new Classification(ClassificationRank.FOUR_OF_A_KIND, cards);
        }
        //Если мы не нашли четверку, ищем фулл хауз
        return detectFullHouse();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification detectFullHouse() {
        //Если карты соответствуют условию фуллхауза, то
        if (this.rankGroup.getSetCount() == 2 ||
                (this.rankGroup.getSetCount() == 1 && this.rankGroup.getPairCount() >= 1)) {
            final Iterator<Map.Entry<Rank, List<Card>>> handRankIterator = this.rankGroup.iterator();
            final SortedSet<Card> cards = new TreeSet<>();
            //Добавляем в коллекцию соответствующие карты
            cards.addAll(handRankIterator.next().getValue());
            cards.addAll(extractFullHousePair(handRankIterator));
            //Возвращаем фуллхауз
            return new Classification(ClassificationRank.FULL_HOUSE, cards);
        }
        //Если не нашли фуллхауз, ищем флеш
        return detectFlush();
    }

    //Метод поиска пар в фуллхаузе
    private static Collection<Card> extractFullHousePair(final Iterator<Map.Entry<Rank, List<Card>>> handRankIterator) {
        //Создаем лист для хранения пар фулл хауза
        final List<Card> fullHousePair = new ArrayList<>();
        //Создаем лист для хранения пары или сета
        final List<Card> pairOrSet = handRankIterator.next().getValue();
        //Если карты три, то
        if (pairOrSet.size() == 3) {
            //Добавляем их в лист хранения пар
            final Iterator<Card> remainingCardsIterator = pairOrSet.iterator();
            fullHousePair.add(remainingCardsIterator.next());
            fullHousePair.add(remainingCardsIterator.next());
        }
        //Если их две, то добавляем их все
        else if (pairOrSet.size() == 2) {
            fullHousePair.addAll(pairOrSet);
        } else {
            throw new RuntimeException("Should not reach here!");
        }
        //Возвращаем пары фуллхауза
        return fullHousePair;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification detectFlush() {
        final Map<Suit, List<Card>> suitGroup = this.suitGroup.getSuitMap();
        for (final Map.Entry<Suit, List<Card>> entry : suitGroup.entrySet()) {
            //Если нашлось пять карт одной масти, то возвращаем новую классификацию флеша
            if (entry.getValue().size() == 5) {
                return new Classification(ClassificationRank.FLUSH, new TreeSet<>(entry.getValue()));
            }
        }
        //Если не нашли, то ищем колеса
        return detectWheel();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification detectWheel() {
        final List<Rank> wheelRanks = Arrays.asList(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE);
        //Создадим коллекцию рангов из руки
        final Set<Rank> handRanks = new TreeSet<>(this.rankGroup.getRankMap().keySet());
        //Исключим лишнее
        handRanks.retainAll(wheelRanks);
        //Если рука содержит все элементы колеса, то добавим все в нашу классификацию
        if (handRanks.containsAll(wheelRanks)) {
            final SortedSet<Card> cards = new TreeSet<>();
            for (final Map.Entry<Rank, List<Card>> entry : this.rankGroup.getRankMap().entrySet()) {
                if (wheelRanks.contains(entry.getKey())) {
                    cards.add(entry.getValue().iterator().next());
                }
            }
            if (cards.size() != 5) {
                throw new RuntimeException("something went wrong!");
            }
            return new Classification(ClassificationRank.WHEEL, cards);
        }
        //Если не нашли колеса, то ищем стрит
        return detectNormalStraight();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification detectNormalStraight() {
        final Set<Rank> cardRanks = this.rankGroup.getRankMap().keySet();

        //Карты от числа до числа? Если да, возвращаем классификацию
        if (cardRanks.containsAll(PokerHandUtils.STRAIGHT_TEN_TO_ACE)) {
            return new Classification(ClassificationRank.STRAIGHT, calculateStraight(PokerHandUtils.STRAIGHT_TEN_TO_ACE));
        } else if (cardRanks.containsAll(PokerHandUtils.STRAIGHT_NINE_TO_KING)) {
            return new Classification(ClassificationRank.STRAIGHT, calculateStraight(PokerHandUtils.STRAIGHT_NINE_TO_KING));
        } else if (cardRanks.containsAll(PokerHandUtils.STRAIGHT_EIGHT_TO_QUEEN)) {
            return new Classification(ClassificationRank.STRAIGHT, calculateStraight(PokerHandUtils.STRAIGHT_EIGHT_TO_QUEEN));
        } else if (cardRanks.containsAll(PokerHandUtils.STRAIGHT_SEVEN_TO_JACK)) {
            return new Classification(ClassificationRank.STRAIGHT, calculateStraight(PokerHandUtils.STRAIGHT_SEVEN_TO_JACK));
        } else if (cardRanks.containsAll(PokerHandUtils.STRAIGHT_SIX_TO_TEN)) {
            return new Classification(ClassificationRank.STRAIGHT, calculateStraight(PokerHandUtils.STRAIGHT_SIX_TO_TEN));
        } else if (cardRanks.containsAll(PokerHandUtils.STRAIGHT_FIVE_TO_NINE)) {
            return new Classification(ClassificationRank.STRAIGHT, calculateStraight(PokerHandUtils.STRAIGHT_FIVE_TO_NINE));
        } else if (cardRanks.containsAll(PokerHandUtils.STRAIGHT_FOUR_TO_EIGHT)) {
            return new Classification(ClassificationRank.STRAIGHT, calculateStraight(PokerHandUtils.STRAIGHT_FOUR_TO_EIGHT));
        } else if (cardRanks.containsAll(PokerHandUtils.STRAIGHT_THREE_TO_SEVEN)) {
            return new Classification(ClassificationRank.STRAIGHT, calculateStraight(PokerHandUtils.STRAIGHT_THREE_TO_SEVEN));
        } else if (cardRanks.containsAll(PokerHandUtils.STRAIGHT_TWO_TO_SIX)) {
            return new Classification(ClassificationRank.STRAIGHT, calculateStraight(PokerHandUtils.STRAIGHT_TWO_TO_SIX));
        }

        //Если не стрит, ищем тройку
        return isSet();
    }

    private SortedSet<Card> calculateStraight(final List<Rank> ranks) {
        final SortedSet<Card> results = new TreeSet<>();
        final Map<Rank, List<Card>> rankGroup = this.rankGroup.getRankMap();
        for (final Rank rank : ranks) {
            final List<Card> values = rankGroup.get(rank);
            if (values != null) {
                results.add(values.iterator().next());
            }
        }
        return results;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification isSet() {
        //Если тройка одна, то добавляем соответствующие карты в классификацию
        if (this.rankGroup.getSetCount() == 1) {
            final Iterator<Map.Entry<Rank, List<Card>>> rankGroup = this.rankGroup.iterator();
            final SortedSet<Card> cards = new TreeSet<>();
            cards.addAll(rankGroup.next().getValue());
            cards.addAll(rankGroup.next().getValue());
            cards.addAll(rankGroup.next().getValue());
            return new Classification(ClassificationRank.SET, cards);
        }
        //Если нет троек, ищем две пары
        return detectTwoPair();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification detectTwoPair() {
        //Если на руке две пары, то добавляем их в классификацию
        if (this.rankGroup.getPairCount() == 2) {
            final Iterator<Map.Entry<Rank, List<Card>>> rankGroup = this.rankGroup.iterator();
            final SortedSet<Card> cards = new TreeSet<>();
            cards.addAll(rankGroup.next().getValue());
            cards.addAll(rankGroup.next().getValue());
            cards.addAll(rankGroup.next().getValue());
            return new Classification(ClassificationRank.TWO_PAIR, cards);
        }
        //Если нет двух пар, то ищем хотя бы одну
        return isPair();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Classification isPair() {
        //Если пара одна, то добавляем ее в классификацию
        if (this.rankGroup.getPairCount() == 1) {
            final Iterator<Map.Entry<Rank, List<Card>>> rankGroup = this.rankGroup.iterator();
            final SortedSet<Card> cards = new TreeSet<>();
            cards.addAll(rankGroup.next().getValue());
            cards.addAll(rankGroup.next().getValue());
            cards.addAll(rankGroup.next().getValue());
            cards.addAll(rankGroup.next().getValue());
            return new Classification(ClassificationRank.PAIR, cards);
        }
        //Если нет пары, то ищем наибольшую карту
        return new Classification(ClassificationRank.HIGH_CARD, calculateHighCards());
    }

    //Ищем кикер
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static Card extractQuadKicker(final Iterator<Map.Entry<Rank, List<Card>>> rankGroup) {
        if (!rankGroup.hasNext()) {
            throw new RuntimeException("No kicker to extract!");
        }
        final SortedSet<Card> remainingCards = new TreeSet<>();
        rankGroup.forEachRemaining(rankListEntry -> remainingCards.addAll(rankListEntry.getValue()));
        return remainingCards.last();
    }

    private static void validateCards(final SortedSet<Card> cards) {
        if (cards.size() != 5) {
            throw new RuntimeException("Invalid cards: " + cards);
        }
    }
}