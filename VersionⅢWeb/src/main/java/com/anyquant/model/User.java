package com.anyquant.model;

import com.anyquant.model.StockTrade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiayiwu on 16/6/6.
 */
@Entity
@Table(name = "t_customer")
public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private double total=10000;
    private double stockPrice=0;
    private double cash=10000;
    private List<StockTrade> list = new ArrayList<StockTrade>();


    public User() {
    }

    public User(double cash, double stockPrice, double all, String password, String name, String username) {
        this.cash = cash;
        this.stockPrice = stockPrice;
        this.total = all;
        this.password = password;
        this.name = name;
        this.username = username;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = round(total,2,BigDecimal.ROUND_CEILING);
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = round(stockPrice,2,BigDecimal.ROUND_CEILING);
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = round(cash,2,BigDecimal.ROUND_CEILING);
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "stockList")
    public List<StockTrade> getList() {
        return list;
    }

    public void setList(List<StockTrade> list) {
        this.list = list;
    }

    private static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }
}

