package vo;

import org.jfree.data.time.Day;

/**
 * Created by HP on 2016/4/5.
 */
public class MAVO {

    private double value;
    private Day date;

    public MAVO(double value, Day date) {
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Day getDate() {
        return date;
    }

    public void setDate(Day date) {
        this.date = date;
    }
}
