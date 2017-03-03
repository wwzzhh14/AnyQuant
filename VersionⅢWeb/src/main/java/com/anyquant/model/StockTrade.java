package com.anyquant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Jiayiwu on 16/6/7.
 */
@Entity
@Table(name = "t_stock")
public class StockTrade {
    private String codeNum;
    private String stockName;
    private int num;
    private double price;


    public StockTrade() {
    }



    public StockTrade(String codeNum, String stockName, int num, double price) {
        this.codeNum = codeNum;
        this.stockName = stockName;
        this.num = num;
        this.price = price;
    }


    @Id
    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
