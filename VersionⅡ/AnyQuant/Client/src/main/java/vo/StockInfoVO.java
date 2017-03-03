package vo;

/**
 * Created by HP on 2016/3/2.
 * modified by JiayiWu on 2016/3/5
 * name:代号、
 * codeNum:股票机构代码
 * date:日期、
 * open:开盘价、
 * high:最高价、
 * low:最低价、
 * close:收盘价、
 * adj_price:后复权价、
 * volume:成交量、
 * turnover:换手率、
 * pe:市盈率、
 * pb:市净率
 */
public class StockInfoVO {
    private String name;
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
    private double  pe;
    private double  pb;

    public StockInfoVO(String name,String codeNum, double pb, double pe, double turnover, double volume,
                           double adj_price, double close, double low, double high, double open, String date) {
        this.name = name;
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
        this.date = date;
    }

    public String getName() {
        return name;
    }
    
    public String getCodeNum() {
		return codeNum;
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

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPe(double pe) {
        this.pe = pe;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }

	
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}
    
    
}
