package com.poker.holdem.logic;


import com.poker.holdem.logic.player.Player;

import java.util.ArrayList;

public class PlayerSort {

    public static ArrayList<Player> getSortedPlayers(ArrayList<Player> serv){
        ArrayList<Player> players= new ArrayList<>();
        int youPos = findYou(serv);
        players.add(serv.get(youPos));
        players.addAll(serv.subList(youPos + 1, serv.size()-1));
        if (youPos != 0) {
            players.addAll(serv.subList(0, youPos - 1));
        }
        return players;
    }

    private static int findYou(ArrayList<Player> players) {
        for (int i= 0; i< players.size(); i++){
            if (players.get(i).getName().equals("iii")){
                return i;
            }
        }
        throw new RuntimeException("Don't find player");

    }
}
