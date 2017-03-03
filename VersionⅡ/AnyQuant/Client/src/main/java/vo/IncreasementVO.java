package vo;

import org.jfree.data.time.Day;

/**
 * Created by HP on 2016/4/2.
 */
public class IncreasementVO {

    private Day date;
    private double increasement;

    public IncreasementVO(Day date, double increasement) {
        this.date = date;
        this.increasement = increasement;
    }

    public Day getDate() {
        return date;
    }

    public void setDate(Day date) {
        this.date = date;
    }

    public double getIncreasement() {
        return increasement;
    }

    public void setIncreasement(double increasement) {
        this.increasement = increasement;
    }
}
