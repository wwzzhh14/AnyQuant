package config;

import java.util.HashMap;

/**
 * Created by HP on 2016/3/30.
 */
public class IndustryStockName {
    private static String[] InsuranceStockName = {"新华保险","中国太保","中国人寿","中国平安"};
    private static String[] InsuranceStockCode = {"sh601336","sh601601","sh601628","sh601318"};
    private static HashMap<String,String> InsuranceStockMap;

    private static HashMap<String,String> TradeStockMap;
    private static String[] TradeStockName = {"江苏国泰","新华锦","汇鸿集团","申达股份","怡亚通","东方创业","中成股份",
            "华升股份","厦门国贸","弘业股份","物产中大","五矿发展","兰生股份","广东明珠","如意集团","时代万恒","上海物贸","浙江东方","建发股份"};
    private static String[] TradeStockCode = {"sh002091","sh600735","sh600981","sh600626","sh002138","sh600278","sh000151","sh600156",
            "sh600755","sh600128","sh600704","sh600058","sh600826","sh600382","sh000626","sh600241","sh600822","sh600120","sh600153"};

    private static HashMap<String,String> DiversifiedFinancialStockMap;
    private static String[] DiversifiedFinancialStockName = {"陕国投A","华铁科技","经纬纺机","中航资本","香溢融通","民生控股","锦龙股份","九鼎投资",
            "鲁银投资","西水股份","ST宏盛","爱建集团","渤海金控"};
    private static String[] DiversifiedFinancialStockCode = {"sh000563","sh603300","sh000666","sh600705","sh600838","sh000416","sh000712",
            "sh600053","sh600784","sh600291","sh600817","sh600643","sh000415"};

    private static HashMap<String,String> TelecomStockMap;
    private static String[] TelecomStockName = {"光环新网","国脉科技","二六三","网宿科技","鹏博士","中国联通"};
    private static String[] TelecomStockCode = {"sh300383","sh002093","sh002467","sh300017","sh600804","sh600050"};

    private static HashMap<String,String> NobleMetalStockMap;
    private static String[] NobleMetalStockName = {"园城黄金","山东金表","ST荣华","刚泰控股","中润资源","西部黄金","湖南黄金","恒邦股份","赤峰黄金",
            "中金黄金","山东黄金","紫金矿业"};
    private static String[] NobleMetalStockCode = {"sh600766","sh600385","sh600311","sh600687","sh000506","sh601069","sh002155","sh002237","sh600988","sh600489","sh600547","sh601899"};



    public static HashMap<String,String> getInsuranceStockNameAndCode(){
        InsuranceStockMap=new HashMap<String, String>();
        for(int i=0;i<InsuranceStockName.length;i++){
            InsuranceStockMap.put(InsuranceStockCode[i],InsuranceStockName[i]);
        }
        return InsuranceStockMap;
    }
    public static HashMap<String,String> getTradeStockNameAndCode(){
        TradeStockMap=new HashMap<String, String>();
        for(int i=0;i< TradeStockName.length;i++){
            TradeStockMap.put( TradeStockCode[i], TradeStockName[i]);
        }
        return  TradeStockMap;
    }
    public static HashMap<String,String> getDiversifiedFinancialStockNameAndCode(){
        DiversifiedFinancialStockMap=new HashMap<String, String>();
        for(int i=0;i<DiversifiedFinancialStockName.length;i++){
            DiversifiedFinancialStockMap.put(DiversifiedFinancialStockCode[i],DiversifiedFinancialStockName[i]);
        }
        return DiversifiedFinancialStockMap;
    }
    public static HashMap<String,String> getTelecomStockNameAndCode(){
        TelecomStockMap=new HashMap<String, String>();
        for(int i=0;i<TelecomStockName.length;i++){
            TelecomStockMap.put(TelecomStockCode[i],TelecomStockName[i]);
        }
        return TelecomStockMap;
    }
    public static HashMap<String,String> getNobleMetalStockNameAndCode(){
        NobleMetalStockMap=new HashMap<String, String>();
        for(int i=0;i<NobleMetalStockName.length;i++){
            NobleMetalStockMap.put(NobleMetalStockCode[i],NobleMetalStockName[i]);
        }
        return NobleMetalStockMap;
    }

}
