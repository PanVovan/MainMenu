package com.poker.holdem.server.deserialization.restore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestoreRPPlayer {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("money")
    @Expose
    private Integer money;
    @SerializedName("picture")
    @Expose
    private Integer picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getPicture() {
        return picture;
    }

    public void setPicture(Integer picture) {
        this.picture = picture;
    }
}
