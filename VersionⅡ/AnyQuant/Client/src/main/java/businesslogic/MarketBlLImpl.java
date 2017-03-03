
package businesslogic;

import businesslogicservice.MarketBLService;
import config.IndustryStockName;
import config.StockName;
import net.MarketNetImpl;
import net.StockNetImpl;
import netservice.MarketNetService;
import netservice.StockNetService;
import org.jfree.data.time.Day;
import po.BenchMarkInfoPO;
import po.NowTimeBenchMarkInfoPO;
import po.StockInfoPO;
import stockutil.TimeUtil;
import vo.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by HP on 2016/3/5.
 * Modified by Jiayiwu on 2016/3/11
 */
public class MarketBlLImpl implements MarketBLService{

    private MarketNetService marketNet=new MarketNetImpl();
    private StockNetService stockNet=new StockNetImpl();
    private static ArrayList<QuadrantDiagramVO> quadrantDiagramVOs=null;

    public ArrayList<String> getAllBenchMark() {
        return marketNet.getAllBenchMark();
    }

    public ArrayList<BenchMarkInfoVO> getBenchMarkInfoByTimeOrByMarket(String startTime, String endTime, String benchmark) {
        ArrayList<BenchMarkInfoPO> poList=marketNet.getBenchMarkInfoByTimeOrByMarket(startTime,endTime,benchmark);
        ArrayList<BenchMarkInfoVO> voList=new ArrayList<BenchMarkInfoVO>();
        if(poList!=null){
            for(BenchMarkInfoPO po:poList){
                if(po!=null){
                    voList.add(new BenchMarkInfoVO(po.getDate(),po.getOpen(),po.getHigh(),po.getClose(),po.getLow(),po.getVolume(),po.getAdj_price()));
                }
            }
            return voList;
        }
        return null;
    }


    public ArrayList<BenchMarkInfoVO> getBenchMarkInfoByTimeOrBySelect(String startTime,
                                                                       String endTime, String benchmark,
                                                                       SelectValueBean open, SelectValueBean high, SelectValueBean close,
                                                                       SelectValueBean low, SelectValueBean volume, SelectValueBean adj_price) {

        ArrayList<BenchMarkInfoVO> result = getBenchMarkInfoByTimeOrByMarket(startTime,endTime,benchmark);

        if(null != open)
            result = selection(result.iterator(),open.getStart(),open.getEnd(),"Open");

        if(null != high)
            result = selection(result.iterator(),high.getStart(),high.getEnd(),"High");
        if(null != low)
            result = selection(result.iterator(),low.getStart(),low.getEnd(),"Low");
        if(null != close)
            result = selection(result.iterator(),close.getStart(),close.getEnd(),"Close");
        if(null != adj_price)
            result = selection(result.iterator(),adj_price.getStart(),adj_price.getEnd(),"Adj_price");
        if(null != volume)
            result = selection(result.iterator(),volume.getStart(),volume.getEnd(),"Volume");



        return result;
    }

    public ArrayList<HistoryInfoVO> getHistoryInfo(String benchmark) {
        Calendar c=Calendar.getInstance();
        long date_2=c.getTimeInMillis();
        long date_1=date_2-1000*60*60*24*20;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        c.setTimeInMillis(date_1);
        String startTime=sdf.format(c.getTime());
        c.setTimeInMillis(date_2);
        String endTime=sdf.format(c.getTime());

        ArrayList<BenchMarkInfoPO> poList=marketNet.getBenchMarkInfoByTimeOrByMarket(startTime,endTime,benchmark);
        ArrayList<HistoryInfoVO> voList=new ArrayList<HistoryInfoVO>();
        if(poList!=null) {
            for (int i = 0; i < poList.size() - 1; i++) {
                BenchMarkInfoPO po1 = poList.get(i);
                BenchMarkInfoPO po2 = poList.get(i + 1);
                voList.add(new HistoryInfoVO(po2.getVolume(), po2.getClose(), (po2.getClose() * po2.getVolume()), po2.getDate(), po2.getClose() >= po1.getClose()));
            }
            return voList;
        }
        return null;
    }

    public NowTimeBenchMarkInfoVO getNowTimeBenchMarkInfo() {
        NowTimeBenchMarkInfoPO po=marketNet.getNowTimeBenchMarkInfo();
        if(po!=null) {
            NowTimeBenchMarkInfoVO vo = new NowTimeBenchMarkInfoVO(po.getDealNum(), po.getDealPri(), po.getHighPri(), po.getIncrePer(), po.getIncrease()
                    , po.getLowpri(), po.getName(), po.getNowpri(), po.getOpenPri(), po.getTime(), po.getYesPri());
            return vo;
        }
        return null;
    }

    public ArrayList<QuadrantDiagramVO> getQuadrantDiagramInfo(String startDate, int days) {
        quadrantDiagramVOs=new ArrayList<QuadrantDiagramVO>();
        HashMap<String,HashMap<String,String>> nameMap=new HashMap<String, HashMap<String, String>>();
        nameMap.put("Multiple Finance",IndustryStockName.getDiversifiedFinancialStockNameAndCode());
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
                ArrayList<StockInfoPO> poList=stockNet.getStockInfoByTime((String)industryName[j], TimeUtil.getPassedDate(days*3/2,startDate),startDate);
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
                ArrayList<StockInfoPO> poList=stockNet.getStockInfoByTime((String)industryName[j], TimeUtil.getPassedDate(2*days,startDate),TimeUtil.getPassedDate(days,startDate));
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
               quadrantDiagramVOs.add(new QuadrantDiagramVO((String)name[i],((volumeList1.get(volumeList1.size()-1)-volumeList2.get(volumeList2.size()-1))/volumeList2.get(volumeList2.size()-1)),((pbList1.get(pbList1.size()-1)-pbList2.get(pbList2.size()-1))/pbList2.get(pbList2.size()-1))));
           }

        }
        return quadrantDiagramVOs;
    }
    public ArrayList<QuadrantDiagramVO> getQuadrantDiagramInfo(){
        return quadrantDiagramVOs;
    }

    public ArrayList<IncreasementVO> getMartketInscreasment(String startTime, String endTime,String marketName) {
        ArrayList<BenchMarkInfoPO> poList=marketNet.getBenchMarkInfoByTimeOrByMarket(TimeUtil.getPassedDate(1,startTime),endTime,marketName);
        ArrayList<IncreasementVO> voList=new ArrayList<IncreasementVO>();
        for(int i=0;i<poList.size()-1;i++){
            String[] date=poList.get(i+1).getDate().split("-");
            voList.add(new IncreasementVO(new Day(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0])),(poList.get(i+1).getClose()-poList.get(i).getClose())/poList.get(i).getClose()));
        }
        return voList;
    }

    public ArrayList<MAVO> getMA(int days, String startTime, String endTime, String marketName) {
        ArrayList<MAVO> voList=new ArrayList<MAVO>();
        ArrayList<BenchMarkInfoPO> poList=marketNet.getBenchMarkInfoByTimeOrByMarket(TimeUtil.getPassedDate((double)days*2,startTime),endTime,marketName);
        if ((TimeUtil.getTimeLong(startTime)-TimeUtil.getTimeLong(endTime))/1000/60/60/24+days>=poList.size()){
            poList=marketNet.getBenchMarkInfoByTimeOrByMarket(TimeUtil.getPassedDate((double)days*4,startTime),endTime,marketName);
        }
        double sum=0;
        if(poList.size()>0){
            for(int i=poList.size()-1;i>=poList.size()-days;i--){
                sum += poList.get(i).getClose();
            }
            double avg=sum/(double) days;
            String[] date=poList.get(poList.size()-1).getDate().split("-");
            voList.add(new MAVO(avg,new Day(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]))));
            int i=poList.size()-1;
            long startLong=TimeUtil.getTimeLong(startTime);
            long endLong=TimeUtil.getTimeLong(endTime);
            while ((i-days)>0&&TimeUtil.getTimeLong(poList.get(i).getDate())<=endLong&&TimeUtil.getTimeLong(poList.get(i).getDate())>=startLong){
                sum -= poList.get(i).getClose();
                sum += poList.get(i-days).getClose();
                String[] date1=poList.get(i-1).getDate().split("-");

                voList.add(new MAVO(sum / (double) days, new Day(Integer.parseInt(date1[2]),Integer.parseInt(date1[1]),Integer.parseInt(date1[0]))));
                i--;
            }
            voList.remove(voList.size()-1);
        }

        return voList;
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
//        for(double d:list){
//            System.out.print(d+"　");
//        }
//        System.out.println()
        return list.get(list.size()/2);
    }


    private ArrayList<BenchMarkInfoVO> selection(Iterator<BenchMarkInfoVO> it, double start, double end, String method) {
        method ="get"+method;
        ArrayList<BenchMarkInfoVO> result = new ArrayList<BenchMarkInfoVO>();
        while (it.hasNext()) {
            BenchMarkInfoVO tem = it.next();
            Class relection = tem.getClass();
            Method m = null;
            try {
                m = relection.getDeclaredMethod(method);
                double num =(Double) m.invoke(tem);
                if(num<=end&&num>=start) {

                    result.add(tem);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }


        return result;
    }
}