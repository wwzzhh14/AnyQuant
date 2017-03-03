package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/11/13.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class Data {
    String Deviceid;
    String Securityid;
    String Userid;
    String Date;
    double Weight;
    double BFR;
    double Calorie;
    double Walkmile;
    double [] Sport_percent = new double[5];
    double Sleep_hour;
    double [] Sleep_percent = new double[3];

    public Data(String deviceid, String securityid, String userid, String date, double weight,
                double BFR, double calorie,
                double walkmile, double sport_percent0, double sport_percent1, double sport_percent2, double sport_percent3, double sport_percent4,
                double sleep_hour, double sleep_percent0,double sleep_percent1,double sleep_percent2) {
        Deviceid = deviceid;
        Securityid = securityid;
        Userid = userid;
        Date = date;
        Weight = weight;
        this.BFR = BFR;
        Calorie = calorie;
        Walkmile = walkmile;
        Sport_percent[0] = sport_percent0;
        Sport_percent[1] = sport_percent1;
        Sport_percent[2] = sport_percent2;
        Sport_percent[3] = sport_percent3;
        Sport_percent[4] = sport_percent4;
        Sleep_hour = sleep_hour;
        Sleep_percent[0] = sleep_percent0;
        Sleep_percent[1] = sleep_percent1;
        Sleep_percent[2] = sleep_percent2;
    }

    public String getDeviceid() {
        return Deviceid;
    }

    public void setDeviceid(String deviceid) {
        Deviceid = deviceid;
    }

    public String getSecurityid() {
        return Securityid;
    }

    public void setSecurityid(String securityid) {
        Securityid = securityid;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getBFR() {
        return BFR;
    }

    public void setBFR(double BFR) {
        this.BFR = BFR;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public double getCalorie() {
        return Calorie;
    }

    public void setCalorie(double calorie) {
        Calorie = calorie;
    }

    public double getWalkmile() {
        return Walkmile;
    }

    public void setWalkmile(double walkmile) {
        Walkmile = walkmile;
    }

    public double[] getSport_percent() {
        return Sport_percent;
    }

    public void setSport_percent(double[] sport_percent) {
        Sport_percent = sport_percent;
    }

    public double getSleep_hour() {
        return Sleep_hour;
    }

    public void setSleep_hour(double sleep_hour) {
        Sleep_hour = sleep_hour;
    }

    public double[] getSleep_percent() {
        return Sleep_percent;
    }

    public void setSleep_percent(double[] sleep_percent) {
        Sleep_percent = sleep_percent;
    }
}
