package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/5/4.
 */
public class RecommendOrange {
     private String orangeName;
     private String date;
     private String recomendMessage;

    public RecommendOrange(String orangeName, String date, String recomendMessage) {
        this.orangeName = orangeName;
        this.date = date;
        this.recomendMessage = recomendMessage;
    }

    public String getOrangeName() {
        return orangeName;
    }

    public void setOrangeName(String orangeName) {
        this.orangeName = orangeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecomendMessage() {
        return recomendMessage;
    }

    public void setRecomendMessage(String recomendMessage) {
        this.recomendMessage = recomendMessage;
    }
}
