package com.anyquant.vo;

import com.anyquant.model.BenchMarkInfoPO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jiayiwu on 16/6/2.
 */
public class BenchMarkInfoVo {
    private String date;

    private double open;
    private double  high;
    private double  close;
    private double  low;
    private double  volume;

    private double adj_price;

    public BenchMarkInfoVo() {
    }

    public BenchMarkInfoVo(BenchMarkInfoPO po) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.date = formatter.format(po.getDate());
        this.open = po.getOpen();
        this.high = po.getHigh();
        this.close = po.getClose();
        this.low = po.getLow();
        this.volume = po.getVolume();
        this.adj_price = po.getAdj_price();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getAdj_price() {
        return adj_price;
    }

    public void setAdj_price(double adj_price) {
        this.adj_price = adj_price;
    }
}
