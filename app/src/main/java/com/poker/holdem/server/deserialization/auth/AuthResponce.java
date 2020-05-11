package com.poker.holdem.server.deserialization.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Класс сгенерирован сайтом jsonschema2pojo
public class AuthResponce {
    @SerializedName("flag")
    @Expose
    private Boolean flag;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("item")
    @Expose
    private AuthPlayer authPlayer;
    @SerializedName("newauthtoken")
    @Expose
    private String newauthtoken;
    public Boolean getFlag() {
        return flag;
    }
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public AuthPlayer getAuthPlayer() {
        return authPlayer;
    }
    public void setAuthPlayer(AuthPlayer authPlayer) {
        this.authPlayer = authPlayer;
    }
    public String getNewauthtoken() {
        return newauthtoken;
    }
    public void setNewauthtoken(String newauthtoken) {
        this.newauthtoken = newauthtoken;
    }
}
