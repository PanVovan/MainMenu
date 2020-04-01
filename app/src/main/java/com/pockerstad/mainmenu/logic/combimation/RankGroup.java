package com.pockerstad.mainmenu.logic.combimation;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.pockerstad.mainmenu.logic.card.Card;
import com.pockerstad.mainmenu.logic.card.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;


public class RankGroup implements Iterable<Map.Entry<Rank, List<Card>>> {

    private final Map<Rank, List<Card>> rankMap;
    private final int quadCount;
    private final int setCount;
    private final int pairCount;

    public RankGroup(final SortedSet<Card> cards) {
        this.rankMap = initRankGroup(cards);
        this.quadCount = groupCount(4);
        this.setCount = groupCount(3);
        this.pairCount = groupCount(2);
    }

    Map<Rank, List<Card>> getRankMap() {
        return this.rankMap;
    }

    int getQuadCount() {
        return this.quadCount;
    }

    int getSetCount() {
        return this.setCount;
    }

    int getPairCount() {
        return this.pairCount;
    }

    //Инициализация мапы группы рангов
    private static Map<Rank, List<Card>> initRankGroup(final SortedSet<Card> cards) {

        //Условие сортировки мапы
        final Comparator<Map.Entry<Rank, List<Card>>> valueComparator =
                (o1, o2) -> o2.getValue().size() == o1.getValue().size() ? o2.getKey().getRankValue() - o1.getKey().getRankValue() :
                        o2.getValue().size() - o1.getValue().size();

        //Лист мапов
        final List<Map.Entry<Rank, List<Card>>> listOfEntries =
                new ArrayList<>(Stream.of(cards).collect(Collectors.groupingBy(Card::getRank)).entrySet());

        //Отсортируем лист мапов по условию
        Collections.sort(listOfEntries, valueComparator);

        final LinkedHashMap<Rank, List<Card>> sortedResults = new LinkedHashMap<>();

        for (final Map.Entry<Rank, List<Card>> entry : listOfEntries) {
            sortedResults.put(entry.getKey(), entry.getValue());
        }

        return Collections.unmodifiableMap(sortedResults);
    }

    private int groupCount(final int groupSize) {
        int counter = 0;
        for (Map.Entry<Rank, List<Card>> i: rankMap.entrySet()){
            if(i.getValue().size() == groupSize)
                counter++;
        }
        return counter;
    }

    @Override
    public Iterator<Map.Entry<Rank, List<Card>>> iterator() {
        return this.rankMap.entrySet().iterator();
    }


}
