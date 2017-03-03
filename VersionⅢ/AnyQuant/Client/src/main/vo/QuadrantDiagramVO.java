package vo;

/**
 * Created by HP on 2016/3/30.
 */
public class QuadrantDiagramVO {
    private String name;
    private double pe;//市盈率涨幅
    private double sum;//成交量涨幅

    public QuadrantDiagramVO(String name, double sum, double pe) {
        this.name = name;
        this.pe = pe;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
