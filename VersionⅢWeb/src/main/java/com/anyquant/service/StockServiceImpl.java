package com.anyquant.service;

import com.anyquant.config.State;
import com.anyquant.config.StringMessage;
import com.anyquant.dao.StockDao;
import com.anyquant.model.NowTimeStockInfoPO;
import com.anyquant.model.StockInfoPO;
import com.anyquant.utils.JsonDataUtil;
import com.anyquant.vo.RecommendVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/15.
 */

@Service
public class StockServiceImpl implements StockService {
    @Resource
   private StockDao stockDao;
    private JsonDataUtil jsonDataUtil= JsonDataUtil.instance();


    public List<?> tailorCriteriaBetween(String codeNum, String start, String end) {
       return stockDao.tailorCriteriaBetween(StockInfoPO.class,codeNum,start,end);

    }

    public List<?> getStockLength(int up, int end) {
        return stockDao.getStockLength(up,end);
    }

    public List<?> getAllStock() {
        return stockDao.getAllStockLength();
    }

    public NowTimeStockInfoPO getNowTimeStockInfo(String codeNum) {
        StringMessage stringMessage=jsonDataUtil.getNowTimeStockResult(codeNum);
        if(stringMessage.getResult()== State.OK){
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

    public RecommendVO getRecommendVoByPython(String stockName) {
        String path ="mydata/analyse/";
        if(stockName.contains("sh")||stockName.contains("sz")){
            stockName=stockName.substring(2);
        }
        path=path+stockName+".data";
        path=StockServiceImpl.class.getResource("/").getPath()+path;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            //BufferedReader br = new BufferedReader(new FileReader(path));
            RecommendVO vo = new RecommendVO();
            Class<RecommendVO> recommendVOClass = (Class<RecommendVO>) vo.getClass();
            String result="";
            while((result=br.readLine())!=null){

                String [] param = result.split("\\|");

                if(!isOK(param))
                    continue;
                Method[] methods = recommendVOClass.getMethods();
                for(int i = 0;i<methods.length;i++){
                    if(methods[i].getName().contains(param[0])&&methods[i].getName().contains("set")){
                        methods[i].invoke(vo,new Object[]{param});
                    }
                }
            }

            return vo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e ){
            e.printStackTrace();
        }

        return null;
    }
    private boolean isOK(String [] param){
        boolean isMatch=true;
        for(int i = 0;i<param.length;i++){
            if(param[i].equals("暂无数据")) {
                isMatch = false;
                break;
            }
        }
        if(param.length==1)
        {
            isMatch=false;
        }
        return isMatch;
    }


}
