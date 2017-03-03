package net;

import config.ExchangePlace;
import config.State;
import config.StockName;
import config.StringMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import netservice.StockNetService;
import po.NowTimeStockInfoPO;
import po.StockInfoPO;
import util.JsonAnalysisUtil;
import util.JsonDataUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
                        StockInfoPO po1 = (StockInfoPO) po;
                        if(po1.getHigh()>0){
                            resultList.add(po1);
                        }

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
        Calendar c=Calendar.getInstance();
        long date_2 = c.getTimeInMillis();
        long tem = 60*24*30;
        long date_1=date_2-(1000*60*tem);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        c.setTimeInMillis(date_1);
        String startTime=sdf.format(c.getTime());
        c.setTimeInMillis(date_2);
        String endTime=sdf.format(c.getTime());
        return getStockInfoByTime(stock,startTime,endTime);
    }

    public ArrayList<StockInfoPO> getStockInfoByTime(String stock, String startTime, String endTime) {
        StringMessage stringMessage=jsonDataUtil.getStock(startTime,endTime,stock,jsonDataUtil.analysisFields());
        ArrayList<StockInfoPO> poList = new ArrayList<StockInfoPO>();
        if(stringMessage.getResult()==State.OK){
            String jsonArray=JSONObject.fromObject(stringMessage.getData()).getJSONObject("data").getJSONArray("trading_info").toString();
            Object[] poArray= JsonAnalysisUtil.getDTOArray(jsonArray,StockInfoPO.class);
            if(poArray!=null) {
                for (Object po : poArray) {
                    StockInfoPO po1 = (StockInfoPO) po;
                    if(po1.getHigh()>0){
                        poList.add(po1);
                    }

                }
            }
            return poList;
        }
        return null;
    }

    public NowTimeStockInfoPO getNowTimeStockInfo(String codeNum) {
//        private String gid;/*股票编号*/
//        private double increPer;   /*涨跌百分比*/
//        private double increase ;/*涨跌额*/
//        private String name;/*股票名称*/
//        private double todayStartPri;/*今日开盘价*/
//        private double yestodEndPri;/*昨日收盘价*/
//        private double nowPri;/*当前价格*/
//        private double todayMax;/*今日最高价*/
//        private double todayMin;/*今日最低价*/
//        private String date;/*日期*/
//        private String time;/*时间*/
//        private double traNumber;/*成交量*/
//        private double traAmount;/*成交金额*/
        StringMessage stringMessage=jsonDataUtil.getNowTimeStockResult(codeNum);
        if(stringMessage.getResult()==State.OK){
            JSONArray jsonArray=JSONArray.fromObject(stringMessage.getData());
            JSONObject jsonObject1=jsonArray.getJSONObject(0);
            JSONObject jsonObject2=jsonObject1.getJSONObject("data");
            NowTimeStockInfoPO po=new NowTimeStockInfoPO(jsonObject2.getString("gid"),jsonObject2.getDouble("increPer"),jsonObject2.getDouble("increase"),
                    jsonObject2.getString("name"),jsonObject2.getDouble("todayStartPri"),jsonObject2.getDouble("yestodEndPri"),jsonObject2.getDouble("nowPri"),
                    jsonObject2.getDouble("todayMax"),jsonObject2.getDouble("todayMin"),jsonObject2.getString("date"),jsonObject2.getString("time"),
                    jsonObject2.getDouble("traNumber"),jsonObject2.getDouble("traAmount"));
            return  po;

        }
        return null;
    }

}
