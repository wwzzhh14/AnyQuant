package config;

import java.util.HashMap;

/**
 * Created by HP on 2016/3/4.
 */
public class StockName {

    private static String[]StockName={"农业银行","中国银行","光大银行","华夏银行","中信银行","交通银行","工商银行","民生银行","建设银行","北京银行",
            "招商银行","兴业银行","南京银行","浦发银行"};
    private static String[] StockCode = {"sh601288", "sh601988", "sh601818", "sh600015", "sh601998", "sh601328", "sh601398",
            "sh600016", "sh601939", "sh601169", "sh600036", "sh601166", "sh601009",  "sh600000"};
    private static HashMap<String,String> stockMap;

    public static HashMap<String,String> getStockNameAndCode(){
        stockMap=new HashMap<String, String>();
        for(int i=0;i<StockName.length;i++){
            stockMap.put(StockCode[i],StockName[i]);
        }
        return stockMap;
    }
}
