package vo;

import org.jfree.data.time.Day;

/**
 * Created by Jiayiwu on 16/3/20.
 */
public class LineAndHistogramChartVO {
    //界面一在此处应该传入close_price
    private double price;
    //对应日期
    private Day day;

    private double dataNum;

    public LineAndHistogramChartVO(double price, Day day, double dataNum) {
        this.price = price;
        this.day = day;
        this.dataNum = dataNum;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDataNum() {
        return dataNum;
    }

    public void setDataNum(double dataNum) {
        this.dataNum = dataNum;
    }
}
