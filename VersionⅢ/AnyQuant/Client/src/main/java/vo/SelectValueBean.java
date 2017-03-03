package vo;

/**
 * Created by Jiayiwu on 16/3/10.
 */
public class SelectValueBean {
    double start;
    double end;

    public SelectValueBean(double start, double end) {
        this.start = start;
        this.end = end;
    }
    public SelectValueBean() {
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }


}
