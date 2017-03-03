package vo;

/**
 * Created by Jiayiwu on 16/5/4.
 */
public class RecommendStarVO {
    //技术面诊股
   private double tech;
    //资金面诊股
   private double fee;
    //消息面诊股
   private double infomation;
    //行业面诊股
   private double industry;
    //基本面诊股
   private double basic;

    public RecommendStarVO(double tech, double fee, double infomation, double industry, double basic) {
        this.tech = tech;
        this.fee = fee;
        this.infomation = infomation;
        this.industry = industry;
        this.basic = basic;
    }

    public double getTech() {
        return tech;
    }

    public void setTech(int tech) {
        this.tech = tech;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public double getInfomation() {
        return infomation;
    }

    public void setInfomation(int infomation) {
        this.infomation = infomation;
    }

    public double getIndustry() {
        return industry;
    }

    public void setIndustry(int industry) {
        this.industry = industry;
    }

    public double getBasic() {
        return basic;
    }

    public void setBasic(int basic) {
        this.basic = basic;
    }
}
