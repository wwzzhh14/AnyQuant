package po;

/**
 * Created by HP on 2016/3/29.
 */
public class macdPO {
    private String date;
    private double value;

    public macdPO(String date, double value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
