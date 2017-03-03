package net;

import config.ExchangePlace;
import config.State;
import config.StockName;
import config.StringMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import netservice.StockNetService;
import po.StockInfoPO;
import stockutil.JsonAnalysisUtil;
import stockutil.JsonDataUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by HP on 2016/3/3.
 */
public class StockNetImpl implements StockNetService {

    private JsonDataUtil jsonDataUtil=JsonDataUtil.instance();

    public ArrayList<StockInfoPO> getStockByYearOrByPlace(String year, ExchangePlace place) {
        ArrayList<StockInfoPO> resultList=new ArrayList<StockInfoPO>();
        String fields=jsonDataUtil.analysisFields();
        Iterator<String> i=StockName.getStockNameAndCode().keySet().iterator();

        Calendar c=Calendar.getInstance();
        long date_2;
        if(c.get(Calendar.DAY_OF_WEEK)==1){
            date_2=c.getTimeInMillis()-1000*60*60*24*1;
        }else if(c.get(Calendar.DAY_OF_WEEK)==2){
            date_2=c.getTimeInMillis()-1000*60*60*24*2;
        }else {
            date_2 = c.getTimeInMillis();
        }
        long date_1=date_2-1000*60*60*24;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        c.setTimeInMillis(date_1);
        String startTime=sdf.format(c.getTime());
        c.setTimeInMillis(date_2);
        String endTime=sdf.format(c.getTime());

        while (i.hasNext()){
            StringMessage stringMessage=jsonDataUtil.getStock(startTime,endTime,i.next(),fields);
            if(stringMessage.getResult()== State.OK){
                String jsonArray=JSONObject.fromObject(stringMessage.getData()).getJSONObject("data").getJSONArray("trading_info").toString();
                Object[] poArray= JsonAnalysisUtil.getDTOArray(jsonArray,StockInfoPO.class);
                if(poArray!=null) {
                    for (Object po : poArray) {
                       resultList.add((StockInfoPO) po);
                    }
                }
            }else {
                return null;
            }
        }

        return resultList;

    }
    public ArrayList<String> getStockName(String year, ExchangePlace place) {
        ArrayList<String> resultList=new ArrayList<String>();
        StringMessage stringMessage=jsonDataUtil.getAllStocks(Integer.parseInt(year), place);
        if(stringMessage.getResult()== State.OK){
            JSONObject jsonObject=JSONObject.fromObject(stringMessage.getData());
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for(int i=0;i<30;i++){
              resultList.add(jsonArray.getJSONObject(i).getString("name"));
            }
            return resultList;
        }
        return null;
    }

    public ArrayList<StockInfoPO> getStockInfo(String stock) {
        return getStockInfoByTime(stock,"2016-01-01","2016-02-01");
    }

    public ArrayList<StockInfoPO> getStockInfoByTime(String stock, String startTime, String endTime) {
        StringMessage stringMessage=jsonDataUtil.getStock(startTime,endTime,stock,jsonDataUtil.analysisFields());
        ArrayList<StockInfoPO> poList = new ArrayList<StockInfoPO>();
        if(stringMessage.getResult()==State.OK){
            String jsonArray=JSONObject.fromObject(stringMessage.getData()).getJSONObject("data").getJSONArray("trading_info").toString();
            Object[] poArray= JsonAnalysisUtil.getDTOArray(jsonArray,StockInfoPO.class);
            if(poArray!=null) {
                for (Object po : poArray) {
                    poList.add((StockInfoPO) po);
                }
            }
            return poList;
        }
        return null;
    }

}
