package po;

/**
 * Created by HP on 2016/3/2.
 * modified by JiayiWu on 2016/3/5
 */
public class StockInfoPO {

//    {"volume":0,"pb":"","high":10.86,"pe_ttm":"","adj_price":10.80192,"low":10.86,"date":"2015-01-02",
//            "close":10.86,"open":10.86,"turnover":""}
	//股票机构代码
	private String codeNum;
    private String date;
    private double open;
    private double  high;
    private double  low;
    private double  close;
    private double adj_price;
    private double  volume;
    private double turnover;
    private double  pe_ttm;
    private double  pb;

    public StockInfoPO( double pb, double pe_ttm, double turnover, double volume,
                       double adj_price, double close, double low, double high, double open, String date) {
        this.pb = pb;
        this.pe_ttm = pe_ttm;
        this.turnover = turnover;
        this.volume = volume;
        this.adj_price = adj_price;
        this.close = close;
        this.low = low;
        this.high = high;
        this.open = open;
        this.date = date;
    }

    public StockInfoPO(){

    }

    public double getPb() {
        return pb;
    }

    public double getPe_ttm() {
        return pe_ttm;
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

    public String getDate() {
        return date;
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

    public void setPe_ttm(double pe) {
        this.pe_ttm = pe;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }
}
