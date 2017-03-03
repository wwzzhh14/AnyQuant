package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/6/8.
 */
public class MsgInfo {

    private boolean isOk;
    private String result;

    public MsgInfo() {
    }

    public MsgInfo(boolean isOk, String result) {
        this.isOk = isOk;
        this.result = result;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
