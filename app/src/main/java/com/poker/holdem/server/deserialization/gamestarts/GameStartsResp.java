package com.poker.holdem.server.deserialization.gamestarts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.poker.holdem.logic.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStartsResp {
    @SerializedName("roomparams")
    @Expose
    private GameStartsRoomParams roomparams;
    @SerializedName("lead")
    @Expose
    private String lead;

    public Map<String, List<Integer>> getPlayersCardsAsMap(){

        Map<String, List<Integer>> result = new HashMap<>();

        for(GameStartsRPCardsPlayer i: roomparams.getCards().getPlayers())
            result.put(i.getPlayername(), i.getCards());

        return result;
    }

    public ArrayList<Player> getAllAsPlayers(){
        ArrayList<Player> result = new ArrayList<>();

        for(GameStartsRPPlayer i: roomparams.getAllplayers()){
            Player buf = new Player();
            buf.setNumOfPicture( i.getPicture() );
            buf.setName( i.getName() );
            buf.setMoney( i.getMoney() );

            result.add(buf);
        }

        return  result;
    }

    public ArrayList<Player> getGamePlayersAsPlayers(){
        ArrayList<Player> result = new ArrayList<>();

        for(GameStartsRPPlayer i: roomparams.getPlayersingame()){
            Player buf = new Player();
            buf.setNumOfPicture( i.getPicture() );
            buf.setName( i.getName() );
            buf.setMoney( i.getMoney() );

            result.add(buf);
        }

        return  result;
    }


    public GameStartsRoomParams getRoomparams() {
        return roomparams;
    }

    public void setRoomparams(GameStartsRoomParams roomparams) {
        this.roomparams = roomparams;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }
}
