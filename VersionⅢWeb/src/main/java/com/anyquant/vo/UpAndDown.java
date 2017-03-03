package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/5/30.
 */
public class UpAndDown {
    private String codeNum;
    private double percent;


    public UpAndDown() {
    }

    public UpAndDown(String codeNum, double percent) {
        this.codeNum = codeNum;
        this.percent = percent;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
