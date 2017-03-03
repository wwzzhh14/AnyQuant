package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/5/27.
 */
public class KLineVo {
    String date;
    double data[]=new double[4];

    public KLineVo() {
    }

    public KLineVo(String date, double[] data) {
        this.date = date;
        this.data = data;
    }
    public KLineVo(String date, double data1,double data2,double data3,double data4) {
        this.date = date;
        data[0]=data1;
        data[1]=data2;
        data[2]=data3;
        data[3]=data4;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }
}
