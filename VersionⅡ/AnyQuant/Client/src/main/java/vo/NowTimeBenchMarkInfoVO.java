package vo;

/**
 * Created by HP on 2016/3/21.
 */
public class NowTimeBenchMarkInfoVO {

        private double dealNum;/*成交量*/
        private double dealPri;/*成交额*/
        private double highPri;/*最高*/
        private double increPer;/*涨跌百分比*/
        private double increase;/*涨跌幅*/
        private double lowpri;/*最低*/
        private String name;/*名称*/
        private double nowpri;/*当前价格*/
        private double openPri;/*今开*/
        private String time;/*时间*/
        private double yesPri;/*昨收*/

        public NowTimeBenchMarkInfoVO(double dealNum, double dealPri, double highPri, double increPer, double increase,
                                      double lowpri, String name, double nowpri, double openPri, String time, double yesPri) {
            this.dealNum = dealNum;
            this.dealPri = dealPri;
            this.highPri = highPri;
            this.increPer = increPer;
            this.increase = increase;
            this.lowpri = lowpri;
            this.name = name;
            this.nowpri = nowpri;
            this.openPri = openPri;
            this.time = time;
            this.yesPri = yesPri;
        }

        public NowTimeBenchMarkInfoVO(){}

        public double getDealNum() {
            return dealNum;
        }

        public void setDealNum(double dealNum) {
            this.dealNum = dealNum;
        }

        public double getDealPri() {
            return dealPri;
        }

        public void setDealPri(double dealPri) {
            this.dealPri = dealPri;
        }

        public double getHighPri() {
            return highPri;
        }

        public void setHighPri(double highPri) {
            this.highPri = highPri;
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

        public double getLowpri() {
            return lowpri;
        }

        public void setLowpri(double lowpri) {
            this.lowpri = lowpri;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getNowpri() {
            return nowpri;
        }

        public void setNowpri(double nowpri) {
            this.nowpri = nowpri;
        }

        public double getOpenPri() {
            return openPri;
        }

        public void setOpenPri(double openPri) {
            this.openPri = openPri;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public double getYesPri() {
            return yesPri;
        }

        public void setYesPri(double yesPri) {
            this.yesPri = yesPri;
        }
    }

