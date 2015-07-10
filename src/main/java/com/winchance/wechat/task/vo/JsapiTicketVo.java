package com.winchance.wechat.task.vo;

import com.google.gson.annotations.SerializedName;

public class JsapiTicketVo {
    @SerializedName("ticket")
    private String ticket;

    @SerializedName("expires_in")
    private int expiresIn;

    @SerializedName("errcode")
    private int errCode;

    @SerializedName("errmsg")
    private String errMsg;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
