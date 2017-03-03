package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/6/8.
 */
public class ArimaPreVO {

    private String yesterdayP="";
    private String percent="";

    public ArimaPreVO(String yesterdayP, String percent) {
        this.yesterdayP = yesterdayP;
        this.percent = percent;
    }

    public String getYesterdayP() {
        return yesterdayP;
    }

    public void setYesterdayP(String yesterdayP) {
        this.yesterdayP = yesterdayP;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
