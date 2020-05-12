package com.poker.holdem.server.deserialization.enterlobby;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.poker.holdem.logic.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterResp {
    @SerializedName("didenter")
    @Expose
    private Boolean didenter;
    @SerializedName("lobbyinfo")
    @Expose
    private LobbyInfoResp lobbyinfo;


    public Map<String, List<Integer>> getPlayersCardsAsMap(){

        Map<String, List<Integer>> result = new HashMap<>();

        for(LobbyInfoPlayerCardsResp i: lobbyinfo.getCards().getPlayers())
            result.put(i.getPlayername(), i.getCards());

        return result;
    }

    public ArrayList<Player> getAllAsPlayers(){
        ArrayList<Player> result = new ArrayList<>();

        for(LobbyPlayerResp i: lobbyinfo.getAllplayers()){
            Player buf = new Player();
            buf.setNumOfPicture( i.getPicture() );
            buf.setName( i.getPlayername() );
            buf.setMoney( i.getMoney() );

            result.add(buf);
        }

        return  result;
    }

    public ArrayList<Player> getGamePlayersAsPlayers(){
        ArrayList<Player> result = new ArrayList<>();

        for(LobbyPlayerResp i: lobbyinfo.getPlayersingame()){
            Player buf = new Player();
            buf.setNumOfPicture( i.getPicture() );
            buf.setName( i.getPlayername() );
            buf.setMoney( i.getMoney() );

            result.add(buf);
        }

        return  result;
    }



    public Boolean getDidenter() {
        return didenter;
    }

    public void setDidenter(Boolean didenter) {
        this.didenter = didenter;
    }

    public LobbyInfoResp getLobbyinfo() {
        return lobbyinfo;
    }

    public void setLobbyinfo(LobbyInfoResp lobbyinfo) {
        this.lobbyinfo = lobbyinfo;
    }
}
