package com.poker.holdem.server.deserialization.registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegResp {

    @SerializedName("is_reg")
    @Expose
    private Boolean isReg;

    public Boolean getIsReg() {
        return isReg;
    }

    public void setIsReg(Boolean isReg) {
        this.isReg = isReg;
    }
}
