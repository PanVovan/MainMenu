package com.poker.holdem.server.deserialization.restore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.poker.holdem.logic.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestoreResp {
    @SerializedName("didrestore")
    @Expose
    private boolean didrestore;
    @SerializedName("roomparams")
    @Expose
    private RestoreRoomParams roomparams = null;
    @SerializedName("token")
    @Expose
    private String token;

    public Map<String, List<Integer>> getPlayersCardsAsMap(){

        Map<String, List<Integer>> result = new HashMap<>();

        for(RestoreRPCardsPlayer i: roomparams.getCards().getPlayers()){
            Map.Entry<String, List<Integer>> buf = new Map.Entry<String, List<Integer>>() {
                @Override
                public String getKey() {
                    return i.getPlayername()
                }

                @Override
                public List<Integer> getValue() {
                    return i.getCards();
                }

                @Override
                public List<Integer> setValue(List<Integer> value) {
                    return i.getCards();
                }
            };

        }

        return null;
    }

    public ArrayList<Player> getAllAsPlayers(){
        ArrayList<Player> result = new ArrayList<>();

        for(RestoreRPPlayer i: roomparams.getAllplayers()){
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

        for(RestoreRPPlayer i: roomparams.getPlayersingame()){
            Player buf = new Player();
            buf.setNumOfPicture( i.getPicture() );
            buf.setName( i.getName() );
            buf.setMoney( i.getMoney() );

            result.add(buf);
        }

        return  result;
    }


    public void setDidrestore (boolean didrestore){
        this.didrestore = didrestore;
    }
    public void setRoomparams(RestoreRoomParams roomparams){
        this.roomparams = roomparams;
    }

    public boolean getDidrestore (){
        return this.didrestore;
    }
    public RestoreRoomParams getRoomparams(){
        return this.roomparams;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
