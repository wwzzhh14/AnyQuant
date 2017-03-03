package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/5/31.
 */
public class IndustryVo {
    double x;
    double y;
    String date;
    String name;

    public IndustryVo() {
    }

    public IndustryVo(double x, double y, String date, String name) {
        this.x = x;
        this.y = y;
        this.date = date;
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
