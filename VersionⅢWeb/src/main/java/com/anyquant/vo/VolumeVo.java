package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/6/8.
 */
public class VolumeVo {

    private String date;
    private double volume;
    private double total;


    public VolumeVo() {
    }

    public VolumeVo(String date, double volume, double total) {
        this.date = date;
        this.volume = volume;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
