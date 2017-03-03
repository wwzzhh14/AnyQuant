package com.anyquant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by HP on 2016/3/2.
 */


 @Entity
 @Table(name = "market")
public class BenchMarkInfoPO {
    @Id
    private Date date;

    private double open;
    private double  high;
    private double  close;
    private double  low;
    private double  volume;
    @Column(name = "adjprice")
    private double adj_price;

    public BenchMarkInfoPO(Date date, double open, double high, double close, double low, double volume, double adj_price) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.close = close;
        this.low = low;
        this.volume = volume;
        this.adj_price = adj_price;
    }

    public BenchMarkInfoPO(){}

    public Date getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getClose() {
        return close;
    }

    public double getLow() {
        return low;
    }

    public double getVolume() {
        return volume;
    }

    public double getAdj_price() {
        return adj_price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setAdj_price(double adj_price) {
        this.adj_price = adj_price;
    }
}
