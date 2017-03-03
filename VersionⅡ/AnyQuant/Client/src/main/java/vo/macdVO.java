package vo;

import org.jfree.data.time.Day;

/**
 * Created by HP on 2016/3/29.
 */
public class macdVO {
    private Day date;
    private double value;

    public macdVO(Day date, double value) {
        this.date = date;
        this.value = value;
    }

    public Day getDate() {
        return date;
    }

    public void setDate(Day date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
