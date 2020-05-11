package com.poker.holdem.server.serialization;

public class GetJSON {
    public static String namePasswordPicture(
            String name
            ,String password
            ,Integer picture
    ){
        String result = "{\"name\":\"";
        result += name;
        result += "\", \"password\":\"";
        result += password;
        result += "\", \"picture\":\"";
        result += picture;
        result += "\" }";
        return result;
    }
    public static String namePassword(
            String name
            ,String password
    ){
        String result = "{\"name\":\"";
        result += name;
        result += "\", \"password\":\"";
        result += password;
        result += "\" }";
        return result;
    }
    public static String nameAuthToken(
            String name
            ,String authToken
    ){
        String result = "{\"name\":\"";
        result += name;
        result += "\", \"authtoken\":\"";
        result += authToken;
        result += "\" }";
        return result;
    }
    public static String nameLobbynameToken(
            String name
            ,String lobbyname
            ,String token
    ){
        String result = "{\"name\":\"";
        result += name;
        result += "\", \"lobbyname\":\"";
        result += lobbyname;
        result += "\", \"token\":\"";
        result += token;
        result += "\" }";
        return result;
    }
    public static String nameLobbynameTokenRate(
            String name
            ,String lobbyname
            ,String token
            ,String rate
    ){
        String result = "{\"name\":\"";
        result += name;
        result += "\", \"lobbyname\":\"";
        result += lobbyname;
        result += "\", \"token\":\"";
        result += token;
        result += "\", \"rate\":\"";
        result += rate;
        result += "\" }";
        return result;
    }
    public static String namePasswordLobbyname(
            String name
            ,String password
            ,String lobbyname
    ){
        String result = "{\"name\":\"";
        result += name;
        result += "\", \"password\":\"";
        result += password;
        result += "\", \"lobbyname\":\"";
        result += lobbyname;
        result += "\" }";
        return result;
    }
    public static String nameTokenPower(
            String name
            ,String token
            ,long power
    ){
        String result = "{\"name\":\"";
        result += name;
        result += "\", \"token\":\"";
        result += token;
        result += "\", \"power\":\"";
        result += power;
        result += "\" }";
        return result;
    }
    public static String nameToken(
            String name
            ,String token
    ){
        String result = "{\"name\":\"";
        result += name;
        result += "\", \"token\":\"";
        result += token;
        result += "\" }";
        return result;
    }
}
