package com.anyquant.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/4.
 */
public class RecommendVO {

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
    private RecommendStarVO startNum=new RecommendStarVO(0,0,0,0,0);
    //机构推荐
    private List<RecommendOrange>orangeAna = new ArrayList<RecommendOrange>();

    public double getAnalyzeNum() {
        return analyzeNum;
    }

    public void setAnalyzeNum(String []  analyzeNum) {
        if(analyzeNum[1].equals("--"))
            this.analyzeNum = 0;
        else {
            this.analyzeNum = s2d(analyzeNum[1]);
        }
    }

    public String getAnalyzeString() {
        return AnalyzeString;
    }

    public void setAnalyzeString(String[] analyzeString) {
        AnalyzeString = analyzeString[1];
    }

    public String getMTrend() {
        return MTrend;
    }

    public void setMTrend(String[] MTrend) {
        this.MTrend = MTrend[1];
    }

    public String getITrend() {
        return ITrend;
    }

    public void setITrend(String[] ITrend) {
        this.ITrend = ITrend[1];
    }

    public String getLTrend() {
        return LTrend;
    }

    public void setLTrend(String[] LTrend) {
        this.LTrend = LTrend[1];
    }

    public String getAnalString() {
        return AnalString;
    }

    public void setAnalString(String[] analString) {
        AnalString = analString[1];
    }

    public RecommendStarVO getStartNum() {
        return startNum;
    }

    public void setStartNum(String [] startNum) {
        this.startNum = new RecommendStarVO(s2d(startNum[1]),s2d(startNum[2]),s2d(startNum[3]),s2d(startNum[4]),s2d(startNum[5]));
    }

    public List<RecommendOrange> getOrangeAna() {
        return orangeAna;
    }

    public void setOrangeAna(String [] orangeAna) {
        this.orangeAna.add(new RecommendOrange(orangeAna[1],orangeAna[2],orangeAna[3]));
    }


    private double s2d(String param){
        return Double.parseDouble(param);
    }
}


