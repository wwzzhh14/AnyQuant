package com.anyquant.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/31.
 */
public class RecommendVOStart {
    //分析指数 如6.8分
    private double analyzeNum=0;
    //分析语句 如打败了99%股票
    private String AnalyzeString="";
    //短期趋势
    private String MTrend="";
    //中期趋势
    private String ITrend="";
    //长期趋势
    private String LTrend="";
    //分析推荐 如:近期的平均成本为17.24元，股价在成本下方运行。
    // 多头行情中，并且有加速上涨趋势。该公司运营状况尚可，多数机构认为该股长期投资价值较高，
    // 投资者可加强关注。 [诊断日期:2016年05月04日 12:56]
    private String AnalString="";
    //推荐星数
    private double[] start = new double[5];
    //机构推荐
    private List<RecommendOrange> orangeAna = new ArrayList<RecommendOrange>();

    public RecommendVOStart(double analyzeNum, String analyzeString, String MTrend, String ITrend, String LTrend, String analString, double start0,double start1, double start2,double start3,double start4,List<RecommendOrange> orangeAna) {
        this.analyzeNum = analyzeNum;
        AnalyzeString = analyzeString;
        this.MTrend = MTrend;
        this.ITrend = ITrend;
        this.LTrend = LTrend;
        AnalString = analString;
        start[0]=start0;
        start[1]=start1;
        start[2]=start2;
        start[3]=start3;
        start[4]=start4;
        this.orangeAna = orangeAna;
    }

    public double[] getStart() {
        return start;
    }

    public void setStart(double[] start) {
        this.start = start;
    }

    public double getAnalyzeNum() {
        return analyzeNum;
    }

    public void setAnalyzeNum(double analyzeNum) {
        this.analyzeNum = analyzeNum;
    }

    public String getAnalyzeString() {
        return AnalyzeString;
    }

    public void setAnalyzeString(String analyzeString) {
        AnalyzeString = analyzeString;
    }

    public String getMTrend() {
        return MTrend;
    }

    public void setMTrend(String MTrend) {
        this.MTrend = MTrend;
    }

    public String getITrend() {
        return ITrend;
    }

    public void setITrend(String ITrend) {
        this.ITrend = ITrend;
    }

    public String getLTrend() {
        return LTrend;
    }

    public void setLTrend(String LTrend) {
        this.LTrend = LTrend;
    }

    public String getAnalString() {
        return AnalString;
    }

    public void setAnalString(String analString) {
        AnalString = analString;
    }

    public List<RecommendOrange> getOrangeAna() {
        return orangeAna;
    }

    public void setOrangeAna(List<RecommendOrange> orangeAna) {
        this.orangeAna = orangeAna;
    }

    private double s2d(String param){
        return Double.parseDouble(param);
    }
}
