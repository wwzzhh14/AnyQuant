package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/5/28.
 */
public class StockPredictVo {
    String date;
    double close;

    public StockPredictVo() {
    }

    public StockPredictVo(String date, double close) {
        this.date = date;
        this.close = close;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }
}
