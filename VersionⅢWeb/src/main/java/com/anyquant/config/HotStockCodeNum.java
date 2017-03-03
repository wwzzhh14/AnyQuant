package com.anyquant.config;

/**
 * Created by Jiayiwu on 16/5/31.
 */
public class HotStockCodeNum {

    private static String[] hotstockCode={"sh601988","sh601288","sh600030","sh600104","sz002024","sh601398"};
    private static String[] hotstockName={"中国银行","农业银行","中信证券","上汽集团","苏宁云商","工商银行"};
    private HotStockCodeNum(){}

    public static String[] getHotstockCode() {
        return hotstockCode;
    }
    public static String[] getHotstockName() {
        return hotstockName;
    }
}
