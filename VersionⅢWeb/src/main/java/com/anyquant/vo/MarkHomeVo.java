package com.anyquant.vo;

import java.math.BigDecimal;

/**
 * Created by Jiayiwu on 16/5/31.
 */

//Home：{今日最低，今日最高，今日收盘，最高预测，最低预测，收盘预测，昨日最高预测，昨日最低预测，昨日收盘预测，
// 昨日最低，昨日最高，昨日收盘，最高偏离，最低偏离，收盘偏离}

public class MarkHomeVo {
    double todayMin;
    double todayMax;
    double todayClose;
    double highPre;
    double lowPre;
    double closePre;
    double yestDayHigPre;
    double yestDayLowPre;
    double yestDayClosePre;
    double yestDaymin;
    double yestDayMax;
    double yestDayClose;
    double distanceHigh;
    double distanceLow;
    double distanceClose;

    public MarkHomeVo() {
    }

    public MarkHomeVo(double todayMin, double todayMax, double todayClose,
                      double highPre, double lowPre, double closePre, double yestDayHigPre, double yestDayLowPre,
                      double yestDayClosePre, double yestDaymin, double yestDayMax, double yestDayClose, double distanceHigh,
                      double distanceLow, double distanceClose) {
        this.todayMin = todayMin;
        this.todayMax = todayMax;
        this.todayClose = todayClose;
        this.highPre = highPre;
        this.lowPre = lowPre;
        this.closePre = closePre;
        this.yestDayHigPre = yestDayHigPre;
        this.yestDayLowPre = yestDayLowPre;
        this.yestDayClosePre = yestDayClosePre;
        this.yestDaymin = yestDaymin;
        this.yestDayMax = yestDayMax;
        this.yestDayClose = yestDayClose;
        this.distanceHigh = distanceHigh;
        this.distanceLow = distanceLow;
        this.distanceClose = distanceClose;
    }

    public double getTodayMin() {
        return todayMin;
    }

    public void setTodayMin(double todayMin) {
        this.todayMin = todayMin;
    }

    public double getTodayMax() {
        return todayMax;
    }

    public void setTodayMax(double todayMax) {
        this.todayMax = todayMax;
    }

    public double getTodayClose() {
        return todayClose;
    }

    public void setTodayClose(double todayClose) {
        this.todayClose = todayClose;
    }

    public double getHighPre() {
        return highPre;
    }

    public void setHighPre(double highPre) {
        this.highPre = highPre;
    }

    public double getLowPre() {
        return lowPre;
    }

    public void setLowPre(double lowPre) {
        this.lowPre = lowPre;
    }

    public double getClosePre() {
        return closePre;
    }

    public void setClosePre(double closePre) {
        this.closePre = closePre;
    }

    public double getYestDayHigPre() {
        return yestDayHigPre;
    }

    public void setYestDayHigPre(double yestDayHigPre) {
        this.yestDayHigPre = yestDayHigPre;
    }

    public double getYestDayLowPre() {
        return yestDayLowPre;
    }

    public void setYestDayLowPre(double yestDayLowPre) {
        this.yestDayLowPre = yestDayLowPre;
    }

    public double getYestDayClosePre() {
        return yestDayClosePre;
    }

    public void setYestDayClosePre(double yestDayClosePre) {
        this.yestDayClosePre = yestDayClosePre;
    }

    public double getYestDaymin() {
        return yestDaymin;
    }

    public void setYestDaymin(double yestDaymin) {
        this.yestDaymin = yestDaymin;
    }

    public double getYestDayMax() {
        return yestDayMax;
    }

    public void setYestDayMax(double yestDayMax) {
        this.yestDayMax = yestDayMax;
    }

    public double getYestDayClose() {
        return yestDayClose;
    }

    public void setYestDayClose(double yestDayClose) {
        this.yestDayClose = yestDayClose;
    }

    public double getDistanceHigh() {
        return distanceHigh;
    }

    public void setDistanceHigh(double distanceHigh) {
        this.distanceHigh = round(distanceHigh,3,BigDecimal.ROUND_CEILING);
    }

    public double getDistanceLow() {
        return distanceLow;
    }

    public void setDistanceLow(double distanceLow) {
        this.distanceLow = round(distanceLow,3,BigDecimal.ROUND_CEILING);
    }

    public double getDistanceClose() {
        return distanceClose;
    }

    public void setDistanceClose(double distanceClose) {
        this.distanceClose = round(distanceClose,3,BigDecimal.ROUND_CEILING);;
    }

    private static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }
}
