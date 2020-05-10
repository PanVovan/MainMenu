package com.poker.holdem.lobby;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Room {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("rate")
    @Expose
    private Integer rate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

}
