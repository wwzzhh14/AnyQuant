package vo;

/**
 * Created by HP on 2016/3/19.
 */
public class HistoryInfoVO {
//    成交量、成交价、总价
    private double volume,price,sum;
    private String date;
    private boolean up;

    public HistoryInfoVO(double volume, double price, double sum, String date, boolean up) {
        this.volume = volume;
        this.price = price;
        this.sum = sum;
        this.date = date;
        this.up = up;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
