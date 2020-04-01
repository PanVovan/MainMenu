package com.pockerstad.mainmenu.customview;

public class Lobby {
    private String lobbyName;
    private String lobbyCode;
    private String lobbyPlayers;

    public Lobby(String name, String code, String players){
        this.lobbyCode = code;
        this.lobbyName = name;
        this.lobbyPlayers = players;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    public String getLobbyCode() {
        return lobbyCode;
    }

    public void setLobbyCode(String lobbyCode) {
        this.lobbyCode = lobbyCode;
    }

    public String getLobbyPlayers() {
        return lobbyPlayers;
    }

    public void setLobbyPlayers(String lobbyPlayers) {
        this.lobbyPlayers = lobbyPlayers;
    }
}