package com.anyquant.vo;

/**
 * Created by Jiayiwu on 16/5/31.
 */
public class HotStockVo {
    String name;
    String code;
    double increase;

    public HotStockVo() {
    }

    public HotStockVo(String name,String code, double increase) {
        this.name = name;
        this.code=code;
        this.increase = increase;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getIncrease() {
        return increase;
    }

    public void setIncrease(double increase) {
        this.increase = increase;
    }
}
