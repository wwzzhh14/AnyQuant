package vo;

/**
 * Created by HP on 2016/3/2.
 name:代号、
 date:日期、
 open:开盘价、
 close:收盘价、
 high:最高价
 */
public class BenchMarkInfoVO {
    private String date;
    private double open;
    private double  high;
    private double  close;
    private double  low;
    private double  volume;
    private double  adj_price;

    public BenchMarkInfoVO(String date, double open, double high, double close, double low, double volume, double adj_price) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.close = close;
        this.low = low;
        this.volume = volume;
        this.adj_price = adj_price;
    }

    public BenchMarkInfoVO(){}

    public String getDate() {
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

    public void setDate(String date) {
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
