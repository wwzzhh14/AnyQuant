package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/6/6.
 */
public class PersonStock {
    private String name;
    private String code;
    private double price;
    private int num;

    public PersonStock() {
    }

    public PersonStock(String name, String code, double price, int num) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
