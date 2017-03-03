package net;

import config.State;
import config.StringMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import netservice.MarketNetService;
import po.BenchMarkInfoPO;
import po.NowTimeBenchMarkInfoPO;
import util.JsonAnalysisUtil;
import util.JsonDataUtil;

import java.util.ArrayList;

/**
 * Created by HP on 2016/3/3.
 */
public class MarketNetImpl implements MarketNetService {

    JsonDataUtil jsonDataUtil=JsonDataUtil.instance();

    public ArrayList<String> getAllBenchMark() {
        StringMessage stringMessage=jsonDataUtil.getAvailibleBenchmarks();
        ArrayList<String> list=new ArrayList<String>();
        if(stringMessage.getResult()== State.OK){
            JSONObject jsonObject=JSONObject.fromObject(stringMessage.getData());
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray.size();i++){
                list.add(jsonArray.getJSONObject(i).getString("name"));
            }
            return list;
        }
        return null;
    }

    public ArrayList<BenchMarkInfoPO> getBenchMarkInfoByTimeOrByMarket(String startTime, String endTime, String benchmark) {
        StringMessage stringMessage;
        if(startTime!=null&&endTime!=null){
            stringMessage=jsonDataUtil.getBench(startTime,endTime,benchmark,jsonDataUtil.analysisFields());
        }else {
            stringMessage=jsonDataUtil.getBench("2016-01-01","2016-02-01",benchmark,jsonDataUtil.analysisFields());
        }
        ArrayList<BenchMarkInfoPO> poList=new ArrayList<BenchMarkInfoPO>();
        if(stringMessage.getResult()==State.OK){
            String jsonArray=JSONObject.fromObject(stringMessage.getData()).getJSONObject("data").getJSONArray("trading_info").toString();
            Object[] objList= JsonAnalysisUtil.getDTOArray(jsonArray,BenchMarkInfoPO.class);
            for(Object obj:objList){
                poList.add((BenchMarkInfoPO)obj);
            }
            return poList;
        }
        return null;
    }

    public NowTimeBenchMarkInfoPO getNowTimeBenchMarkInfo() {
        StringMessage stringMessage;
        stringMessage=jsonDataUtil.getNowTimeMarketResult();
        if(stringMessage.getResult()==State.OK){
            NowTimeBenchMarkInfoPO po=JsonAnalysisUtil.json2Bean(stringMessage.getData(),NowTimeBenchMarkInfoPO.class);
            return po;
        }
        return null;
    }
}
