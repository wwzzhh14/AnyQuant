package vo;

/**
 * Created by HP on 2016/3/21.
 */
public class NowTimeStockInfoVO {
    private String gid;/*股票编号*/
    private double increPer;   /*涨跌百分比*/
    private double increase ;/*涨跌额*/
    private String name;/*股票名称*/
    private double todayStartPri;/*今日开盘价*/
    private double yestodEndPri;/*昨日收盘价*/
    private double nowPri;/*当前价格*/
    private double todayMax;/*今日最高价*/
    private double todayMin;/*今日最低价*/
    private String date;/*日期*/
    private String time;/*时间*/
    private double traNumber;/*成交量*/
    private double traAmount;/*成交金额*/

    public NowTimeStockInfoVO(String gid, double increPer, double increase, String name, double todayStartPri, double yestodEndPri, double nowPri,
                              double todayMax, double todayMin, String date, String time, double traNumber, double traAmount) {
        this.gid = gid;
        this.increPer = increPer;
        this.increase = increase;
        this.name = name;
        this.todayStartPri = todayStartPri;
        this.yestodEndPri = yestodEndPri;
        this.nowPri = nowPri;
        this.todayMax = todayMax;
        this.todayMin = todayMin;
        this.date = date;
        this.time = time;
        this.traNumber = traNumber;
        this.traAmount = traAmount;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public double getIncrePer() {
        return increPer;
    }

    public void setIncrePer(double increPer) {
        this.increPer = increPer;
    }

    public double getIncrease() {
        return increase;
    }

    public void setIncrease(double increase) {
        this.increase = increase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTodayStartPri() {
        return todayStartPri;
    }

    public void setTodayStartPri(double todayStartPri) {
        this.todayStartPri = todayStartPri;
    }

    public double getYestodEndPri() {
        return yestodEndPri;
    }

    public void setYestodEndPri(double yestodEndPri) {
        this.yestodEndPri = yestodEndPri;
    }

    public double getNowPri() {
        return nowPri;
    }

    public void setNowPri(double nowPri) {
        this.nowPri = nowPri;
    }

    public double getTodayMax() {
        return todayMax;
    }

    public void setTodayMax(double todayMax) {
        this.todayMax = todayMax;
    }

    public double getTodayMin() {
        return todayMin;
    }

    public void setTodayMin(double todayMin) {
        this.todayMin = todayMin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
