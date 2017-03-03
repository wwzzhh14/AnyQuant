package businesslogic;

import businesslogicservice.StockInfoBLService;
import config.ExchangePlace;
import config.StockName;
import data.MACDDataImp;
import dataservice.MACDDataService;
import net.StockNetImpl;
import netservice.StockNetService;
import org.jfree.data.time.Day;
import po.NowTimeStockInfoPO;
import po.StockInfoPO;
import po.macdPO;
import util.TimeUtil;
import vo.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by HP on 2016/3/6.
 * Modified by Jiayiwu on 2016/3/11
 */
public class StockInfoBLImpl implements StockInfoBLService {

    private StockNetService stockNet = new StockNetImpl();
    private String nowStockNum="sh600000";
    private MACDDataService macdData=new MACDDataImp();



    public ArrayList<StockInfoVO> getStockByYearOrByPlace(String year, ExchangePlace place) {
        ArrayList<StockInfoPO> poList = stockNet.getStockByYearOrByPlace(year, place);
        if (poList != null) {
            ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
            if (poList.size() > 0) {
                Object[] stockcode = StockName.getStockNameAndCode().keySet().toArray();
                voList = poToVO(poList, stockcode);
            }
            Collections.reverse(voList);
            return voList;
        }

        return null;
    }

    public ArrayList<StockInfoVO> getStockInfo(String stock) {

        nowStockNum = stock;

        ArrayList<StockInfoPO> poList = stockNet.getStockInfo(stock);
        if (poList != null) {

            ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
            if (poList.size() > 0) {
                voList = poToVO(poList, stock);
            }

            Collections.reverse(voList);
            return voList;
        }
        return null;
    }

    public ArrayList<StockInfoVO> getStockInfoByTime(String stock, String startTime, String endTime) {

        nowStockNum = stock;

        ArrayList<StockInfoPO> poList = stockNet.getStockInfoByTime(stock, startTime, endTime);
        if (poList != null) {
            ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
            if (poList.size() > 0) {
                voList = poToVO(poList, stock);
            }

            Collections.reverse(voList);
            return voList;
        }
        return null;
    }


    private ArrayList<StockInfoVO> poToVO(ArrayList<StockInfoPO> poList, Object[] stockCodes) {
        HashMap<String, String> stockMap = StockName.getStockNameAndCode();
        ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
        for (int i = 0; i < stockCodes.length; i++) {
            StockInfoPO po = (StockInfoPO) poList.get(i);
            if (po != null) {
                voList.add(new StockInfoVO(stockMap.get((String) stockCodes[i]), (String) stockCodes[i], po.getPb(), po.getPe_ttm(), po.getTurnover(), po.getVolume(),
                        po.getAdj_price(), po.getClose(), po.getLow(), po.getHigh(), po.getOpen(), po.getDate()));
            }
        }
        return voList;
    }

    private ArrayList<StockInfoVO> poToVO(ArrayList<StockInfoPO> poList, String stockCode) {
        HashMap<String, String> stockMap = StockName.getStockNameAndCode();
        ArrayList<StockInfoVO> voList = new ArrayList<StockInfoVO>();
        for (StockInfoPO po : poList) {
            if (po != null) {
                voList.add(new StockInfoVO(stockMap.get(stockCode), stockCode, po.getPb(), po.getPe_ttm(), po.getTurnover(), po.getVolume(),
                        po.getAdj_price(), po.getClose(), po.getLow(), po.getHigh(), po.getOpen(), po.getDate()));
            }
        }
        return voList;
    }


    public ArrayList<StockInfoVO> getStockInfoBySelect(String stock, String startTime, String endTime, SelectValueBean open, SelectValueBean high, SelectValueBean low, SelectValueBean close, SelectValueBean adj_price, SelectValueBean volume, SelectValueBean pe, SelectValueBean pb) {

        ArrayList<StockInfoVO> result = getStockInfoByTime(stock,startTime,endTime);

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
        if(null != pe)
            result = selection(result.iterator(),pe.getStart(),pe.getEnd(),"Pe");
        if(null != pb)
            result = selection(result.iterator(),pb.getStart(),pb.getEnd(),"Pb");


        return result;
    }

    public ArrayList<HistoryInfoVO> getHistoryInfo(String codeNum) {
//        double volume, double price, double sum, String date, boolean up
        Calendar c=Calendar.getInstance();
        long date_2=c.getTimeInMillis();
        long date_1=date_2-1000*60*60*24*20;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        c.setTimeInMillis(date_1);
        String startTime=sdf.format(c.getTime());
        c.setTimeInMillis(date_2);
        String endTime=sdf.format(c.getTime());

        ArrayList<StockInfoPO> poList=stockNet.getStockInfoByTime(codeNum,startTime,endTime);
        ArrayList<HistoryInfoVO> voList=new ArrayList<HistoryInfoVO>();
        if(poList!=null) {
            for (int i = 0; i < poList.size() - 1; i++) {
                StockInfoPO po1 = poList.get(i);
                StockInfoPO po2 = poList.get(i + 1);
                voList.add(new HistoryInfoVO(po2.getVolume(), po2.getClose(), (po2.getClose() * po2.getVolume()), po2.getDate(), po2.getClose() >= po1.getClose()));
            }
            return voList;
        }
        return null;
    }

    public NowTimeStockInfoVO getNowTimeStockInfo(String codeNum) {
        NowTimeStockInfoPO po=stockNet.getNowTimeStockInfo(codeNum);
        if(po!=null){
            NowTimeStockInfoVO vo=new NowTimeStockInfoVO(po.getGid(),po.getIncrePer(),po.getIncrease(),po.getName(),po.getTodayStartPri(),po.getYestodEndPri(),
                    po.getNowPri(),po.getTodayMax(),po.getTodayMin(),po.getDate(),po.getTime(),po.getTraNumber(),po.getTraAmount());
            return vo;
        }
        return null;
    }

    public ArrayList<macdVO> getMACDData(String startTime, String endTime, String codeNum, String type) {
        ArrayList<macdVO> voList=new ArrayList<macdVO>();
        List<macdPO> poList=macdData.getData(startTime,endTime,codeNum,type);
        for(macdPO po:poList){
            String[] date=po.getDate().split("-");
            voList.add(new macdVO(new Day(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0])),po.getValue()));
        }
        return voList;
    }

    public void update() {
        macdData.update();
    }

    public ArrayList<IncreasementVO> getStockInscreasment(String startTime, String endTime, String codeNum) {
        ArrayList<StockInfoPO> poList=stockNet.getStockInfoByTime(codeNum,startTime,endTime);
        ArrayList<IncreasementVO> voList=new ArrayList<IncreasementVO>();
        for(int i=0;i<poList.size()-1;i++){
            String[] date=poList.get(i+1).getDate().split("-");
            voList.add(new IncreasementVO(new Day(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0])),(poList.get(i+1).getClose()-poList.get(i).getClose())/poList.get(i).getClose()));
        }
        return voList;
    }

    public ArrayList<MAVO> getMA(int days, String startTime, String endTime,String codeNum) {
        ArrayList<MAVO> voList=new ArrayList<MAVO>();
        ArrayList<StockInfoPO> poList=stockNet.getStockInfoByTime(codeNum,TimeUtil.getPassedDate((double)days*2,startTime),endTime);
        if ((TimeUtil.getTimeLong(startTime)-TimeUtil.getTimeLong(endTime))/1000/60/60/24+days>=poList.size()){
            poList=stockNet.getStockInfoByTime(codeNum,TimeUtil.getPassedDate((double)days*4,startTime),endTime);
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


    private ArrayList<StockInfoVO> selection(Iterator<StockInfoVO> it, double start, double end,String method) {
        method ="get"+method;
        ArrayList<StockInfoVO> result = new ArrayList<StockInfoVO>();
        while (it.hasNext()) {
            StockInfoVO tem = it.next();
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
