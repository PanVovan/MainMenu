package com.pockerstad.mainmenu;


import java.util.ArrayList;

public class PlayerSort {

    ArrayList<Player> players;

    public void setPlayers(ArrayList<Player> players) {
        this.players = sort(players);
    }

    ArrayList<Player> sort(ArrayList<Player> serv){
        ArrayList<Player> players= new ArrayList<>();
        int youPos = findYou(serv);
        players.add(serv.get(youPos));
        players.addAll(serv.subList(youPos + 1, serv.size()));
        players.addAll(serv.subList(1, youPos-1));
        return players;
    }

    private int findYou(ArrayList<Player> players) {
        for (Player player: players){
            if (player.getName().equals("iii")){
                return player.getPos();
            }
        }
        throw new RuntimeException("Don't find player");

    }
}
