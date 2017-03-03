package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/5/28.
 */
public class NowStockVo {
    private double high;/*最高价*/
    private double low;/*最低价*/
    private double todayStart;/*今开*/
    private double yesterdayEnd;/*昨收*/
    private double traNumber;/*成交量*/
    private double traAmount;/*成交金额*/
    private double nowPrice;/*现在价格*/
    private String codeName;/*股票名称*/
    private String date;/*更新时间*/

    public NowStockVo() {
    }

    public NowStockVo(double high, double low, double todayStart, double yesterdayEnd, double traNumber, double traAmount, double nowPrice, String codeName, String date) {
        this.high = high;
        this.low = low;
        this.todayStart = todayStart;
        this.yesterdayEnd = yesterdayEnd;
        this.traNumber = traNumber;
        this.traAmount = traAmount;
        this.nowPrice = nowPrice;
        this.codeName = codeName;
        this.date = date;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getTodayStart() {
        return todayStart;
    }

    public void setTodayStart(double todayStart) {
        this.todayStart = todayStart;
    }

    public double getYesterdayEnd() {
        return yesterdayEnd;
    }

    public void setYesterdayEnd(double yesterdayEnd) {
        this.yesterdayEnd = yesterdayEnd;
    }

    public double getTraNumber() {
        return traNumber;
    }

    public void setTraNumber(double traNumber) {
        this.traNumber = traNumber;
    }

    public double getTraAmount() {
        return traAmount;
    }

    public void setTraAmount(double traAmount) {
        this.traAmount = traAmount;
    }

    public double getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(double nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
