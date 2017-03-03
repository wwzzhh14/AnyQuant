package com.anyquant.service;

import com.anyquant.config.HotStockCodeNum;
import com.anyquant.config.IndustryStockName;
import com.anyquant.config.StockName;
import com.anyquant.controller.StockControll;
import com.anyquant.dao.StockDao;
import com.anyquant.model.NowTimeStockInfoPO;
import com.anyquant.model.StockInfoPO;
import com.anyquant.utils.TimeUtil;
import com.anyquant.vo.HotStockVo;
import com.anyquant.vo.IndustryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Jiayiwu on 16/5/31.
 */
@Service
public class RecommendServiceImpl implements RecommendService {
    @Resource
    StockService stockService;
    @Resource
    StockDao stockDao;//获取所有行业股票数据调用这个就OK
    public List<HotStockVo> getHotStock() {
        String []hotCode=HotStockCodeNum.getHotstockCode();
        List<HotStockVo> result = new ArrayList<HotStockVo>();
        for(int i = 0;i<hotCode.length;i++){
            NowTimeStockInfoPO po =stockService.getNowTimeStockInfo(hotCode[i]);
            if(po!=null)
            result.add(new HotStockVo(po.getName(),hotCode[i],po.getIncrease()));
        }
        return result;
    }

    public List<IndustryVo> getIndustry(String startDate, int days) {
        ArrayList<IndustryVo> quadrantDiagramVOs=new ArrayList<IndustryVo>();
        HashMap<String,HashMap<String,String>> nameMap=new HashMap<String, HashMap<String, String>>();
        nameMap.put("Multiple Finance", IndustryStockName.getDiversifiedFinancialStockNameAndCode());

        nameMap.put("Insurance",IndustryStockName.getInsuranceStockNameAndCode());
        nameMap.put("Nobel Metal",IndustryStockName.getNobleMetalStockNameAndCode());
        nameMap.put("Telicommunications",IndustryStockName.getTelecomStockNameAndCode());
        nameMap.put("Commerce",IndustryStockName.getTradeStockNameAndCode());
        nameMap.put("Banking", StockName.getStockNameAndCode());

        ArrayList<Double> volumeList1=new ArrayList<Double>();
        ArrayList<Double> volumeList2=new ArrayList<Double>();
        ArrayList<Double> pbList1=new ArrayList<Double>();
        ArrayList<Double> pbList2=new ArrayList<Double>();

        Object[] name=nameMap.keySet().toArray();
        for(int i=0;i<name.length;i++){
            String date="";
            HashMap<String,String> map=nameMap.get((String)name[i]);
            Object[] industryName=map.keySet().toArray();
            double sum1=0;
            ArrayList<Double> pbList3=new ArrayList<Double>();
            for(int j=0;j<industryName.length;j++){
                List<?> list= (ArrayList<StockInfoPO>) stockDao.tailorCriteriaBetween(StockInfoPO.class,(String)industryName[j],  TimeUtil.getPassedDate(days*3/2,startDate),startDate);
                ArrayList<StockInfoPO> poList=new ArrayList<StockInfoPO>();
                for(int k = 0;k<list.size();k++){
                    poList.add((StockInfoPO)list.get(k));
                }


                if(poList.size()>0) {
                    sum1 += calVolumeSum(poList);
                    pbList3.add(poList.get(0).getPb());
                }

            }
            if(sum1>0&&pbList3.size()>0){
                volumeList1.add(sum1);
                pbList1.add(getMid(pbList3));
            }


            double sum2=0;
            ArrayList<Double> pbList4=new ArrayList<Double>();
            for(int j=0;j<industryName.length;j++){
//                ArrayList<StockInfoPO> poList=stockNet.getStockInfoByTime((String)industryName[j], TimeUtil.getPassedDate(2*days,startDate),TimeUtil.getPassedDate(days,startDate));
                List<?> list= (ArrayList<StockInfoPO>) stockDao.tailorCriteriaBetween(StockInfoPO.class,(String)industryName[j],  TimeUtil.getPassedDate(2*days,startDate),TimeUtil.getPassedDate(days,startDate));
                ArrayList<StockInfoPO> poList=new ArrayList<StockInfoPO>();
                for(int k = 0;k<list.size();k++){
                    poList.add((StockInfoPO)list.get(k));
                }
                if(poList.size()>0) {
                    sum2 += calVolumeSum(poList);
                    pbList4.add(poList.get(0).getPb());
                }
            }
            if(sum2>0&&pbList4.size()>0){
                volumeList2.add(sum2);
                pbList2.add(getMid(pbList4));
            }
            if(volumeList1.size()>0&&volumeList2.size()>0&&pbList1.size()>0&&pbList2.size()>0){
                quadrantDiagramVOs.add(new IndustryVo(((volumeList1.get(volumeList1.size()-1)-volumeList2.get(volumeList2.size()-1))/volumeList2.get(volumeList2.size()-1)),((pbList1.get(pbList1.size()-1)-pbList2.get(pbList2.size()-1))/pbList2.get(pbList2.size()-1)),

                        startDate,(String)name[i]));
            }

        }

        return quadrantDiagramVOs;
    }

    private double calVolumeSum(List<StockInfoPO> poList){
        double result=0;
        for(StockInfoPO po:poList){
            result+=po.getVolume();
        }
        return result/poList.size();
    }

    private double getMid(List<Double> list){
        list.sort(new Comparator<Double>() {
            public int compare(Double o1, Double o2) {
                if(o1>o2){
                    return 1;
                }else if(o1<o2){
                    return -1;
                }else return 0;
            }
        });
        return list.get(list.size()/2);
    }
}
