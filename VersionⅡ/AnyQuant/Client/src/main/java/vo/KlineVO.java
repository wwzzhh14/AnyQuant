package vo;

import org.jfree.data.time.Day;

/**
 * Created by Jiayiwu on 16/3/18.
 */
public class KlineVO {
    //日期
    private Day day;
    //开盘
    private double open;
    //最高价
    private double high;
    //最低价
    private double low;
    //收盘价
    private double close;
    //成交量
    private double volume;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
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

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }


    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public KlineVO(Day day, double open, double high, double low, double close, double volume) {
        this.day = day;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }
}
