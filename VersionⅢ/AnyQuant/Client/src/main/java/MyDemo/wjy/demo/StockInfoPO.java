package MyDemo.wjy.demo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by HP on 2016/3/2.
 * modified by JiayiWu on 2016/3/5
 */
@Entity
@Table(name = "stock")
public class StockInfoPO  implements Serializable{

//    {"volume":0,"pb":"","high":10.86,"pe_ttm":"","adj_price":10.80192,"low":10.86,"date":"2015-01-02",
//            "close":10.86,"open":10.86,"turnover":""}
	//股票机构代码
    @Id
	private String codeNum;
    @Id
    private Date date;
    private double open;
    private double  high;
    private double  low;
    private double  close;
    private double adj_price;
    private double  volume;
    private double turnover;
    private double  pe;
    private double  pb;

    public StockInfoPO(String codeNum,double pb, double pe, double turnover, double volume,
                       double adj_price, double close, double low, double high, double open, java.util.Date date) {
        this.codeNum = codeNum;
        this.pb = pb;
        this.pe = pe;
        this.turnover = turnover;
        this.volume = volume;
        this.adj_price = adj_price;
        this.close = close;
        this.low = low;
        this.high = high;
        this.open = open;
       this.date =new java.sql.Date(date.getTime());
    }

    public StockInfoPO(){

    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public double getPb() {
        return pb;
    }

    public double getPe() {
        return pe;
    }

    public double getTurnover() {
        return turnover;
    }

    public double getVolume() {
        return volume;
    }

    public double getAdj_price() {
        return adj_price;
    }

    public double getClose() {
        return close;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getOpen() {
        return open;
    }

    public java.sql.Date getDate() {
        return date;
    }


    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public void setAdj_price(double adj_price) {
        this.adj_price = adj_price;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }
}
