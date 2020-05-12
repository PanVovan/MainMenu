package com.poker.holdem.logic.handlogic.combination;

import com.poker.holdem.logic.handlogic.card.Card;
import com.poker.holdem.logic.handlogic.card.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class HandRanks implements Iterable<Map.Entry<Rank, List<Card>>>{

    private Logger logger = Logger.getAnonymousLogger();

    private Map<Rank, List<Card>> rank_cards_map;
    private int four_counter;
    private int set_counter;
    private int pair_counter;

    //класс рангов всех карт в руке
    public HandRanks (final SortedSet<Card> cards){
        this.rank_cards_map = sortRanks(cards);
        //количество пар/троек/четвёрок
        this.four_counter = numberOfGroups(4);
        this.set_counter = numberOfGroups(3);
        this.pair_counter = numberOfGroups(2);


    }

    //get...List возвращает все карты соответствующей
    //комбинации
    public SortedSet<Card> getFourList(){
        SortedSet<Card> result = new TreeSet<>();
        for (Map.Entry<Rank, List<Card>> i: rank_cards_map.entrySet()){
            if(i.getValue().size() == 4)
                result.addAll(i.getValue());
        }
        return result;
    }

    public SortedSet<Card> getSetList(){
        SortedSet<Card> result = new TreeSet<>();
        for (Map.Entry<Rank, List<Card>> i: rank_cards_map.entrySet()){
            if(i.getValue().size() == 3)
                result.addAll(i.getValue());
        }
        return result;
    }

    public SortedSet<Card> getPairList(){
        SortedSet<Card> result = new TreeSet<>();
        for (Map.Entry<Rank, List<Card>> i: rank_cards_map.entrySet()){
            if(i.getValue().size() == 2)
                result.addAll(i.getValue());
        }
        return result;
    }

    //считает количество пар/троек/четвёрок карт
    public int numberOfGroups(int size){
        int counter = 0;
        for (Map.Entry<Rank, List<Card>> i: rank_cards_map.entrySet()){
            if(i.getValue().size() == size)
                counter++;
        }
        return counter;
    }

    //Инициализация мапы группы рангов
    private static Map<Rank, List<Card>> sortRanks(final SortedSet<Card> cards) {

        //Условие сортировки мапы
        final Comparator<Map.Entry<Rank, List<Card>>> valueComparator =
                (o1, o2) -> o2.getValue().size() == o1.getValue().size() ? o2.getKey().getRank_val() - o1.getKey().getRank_val() :
                        o2.getValue().size() - o1.getValue().size();

        //Лист мапов
        final ArrayList<Map.Entry<Rank, List<Card>>> listOfEntries =
                new ArrayList<>(cards.stream().collect(Collectors.groupingBy(Card::getRank)).entrySet());

        //Отсортируем лист листов для создания из них карты по условию
        Collections.sort(listOfEntries, valueComparator);

        final LinkedHashMap<Rank, List<Card>> sortedResults = new LinkedHashMap<>();

        for (final Map.Entry<Rank, List<Card>> entry : listOfEntries) {
            sortedResults.put(entry.getKey(), entry.getValue());
        }

        return Collections.unmodifiableMap(sortedResults);
    }

    public Map<Rank, List<Card>> getRank_cards_map(){
        return this.rank_cards_map;
    }

    public int getFour_counter() {
        return four_counter;
    }

    public int getSet_counter() {
        return set_counter;
    }

    public int getPair_counter() {
        return pair_counter;
    }

    @Override
    public Iterator<Map.Entry<Rank, List<Card>>> iterator() {
        return this.rank_cards_map.entrySet().iterator();
    }
}

