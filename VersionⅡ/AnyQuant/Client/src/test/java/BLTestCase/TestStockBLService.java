package BLTestCase;

import businesslogic.StockInfoBLImpl;
import businesslogic.visualizationlogic.VisualizationStockDataimpl;
import businesslogicservice.StockInfoBLService;
import businesslogicservice.visualizationlogicservice.VisualizationStockData;
import config.ExchangePlace;
import config.StockName;
import junit.framework.TestCase;
import stockutil.TimeUtil;
import vo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2016/3/7.
 */
public class TestStockBLService extends TestCase{


    StockInfoBLService stockInfoBLService=new StockInfoBLImpl();
    private VisualizationStockData vs=new VisualizationStockDataimpl();
//    String year, ExchangePlace place

    ArrayList<String> startTime=new ArrayList<String>();
    ArrayList<String> endTime=new ArrayList<String>();
    ArrayList<String> stockName=new ArrayList<String>();



    public void setUp() {
        startTime.clear();
        endTime.clear();
        stockName.clear();

        startTime.add("2016-01-01");
        endTime.add("2016-01-01");

        startTime.add("2016-01-01");
        endTime.add("2016-04-01");

        startTime.add("2016-01-01");
        endTime.add("2015-01-01");

        startTime.add("2016-01-01");
        endTime.add("2016-05-01");

        startTime.add("2013-01-01");
        endTime.add("2016-01-01");

        stockName.add("sh601288");
        stockName.add("sh601988");
        stockName.add("sh601818");
        stockName.add("sh600015");
        stockName.add("sh601998");
        stockName.add("sh601328");

        System.out.println("**************************");
    }
    public void testGetStockByYearOrByPlace(){
        ArrayList<StockInfoVO> list=null;
        ArrayList<String> year=new ArrayList<String>();
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");
        for(int i=0;i<year.size();i++){
                list=stockInfoBLService.getStockByYearOrByPlace(year.get(i),ExchangePlace.sh);
            System.out.println(year.get(i));
                for(StockInfoVO vo:list){
                    System.out.println("Adj_Date:"+vo.getDate()+"Code_Num:"+vo.getCodeNum()+" "+"Name::"+vo.getName());
                }
        }

    }

    public void testGetStockInfo(){
        ArrayList<StockInfoVO> list=null;
        for(int j=0;j<stockName.size();j++){
            list=stockInfoBLService.getStockInfo(stockName.get(j));
            System.out.println(stockName.get(j));
            for(StockInfoVO vo:list){
                System.out.println("Adj_Date:"+vo.getDate()+"Code_Num:"+vo.getCodeNum()+" "+"Name::"+vo.getName());
            }
        }
    }
//     返回某只股票某段时间内的详细信息


    public void testGetStockInfoByTime(){
        ArrayList<StockInfoVO> list=null;

        for(int i=0;i<startTime.size();i++){
            for(int j=0;j<stockName.size();j++){
                list=stockInfoBLService.getStockInfoByTime(stockName.get(j),startTime.get(i),endTime.get(i));
                for(StockInfoVO vo:list){
                    System.out.println("Adj_Date:"+vo.getDate()+"Code_Num:"+vo.getCodeNum()+" "+"Name::"+vo.getName());
                }
            }
        }

    }

//        public ArrayList<StockInfoVO> getStockInfoBySelect(String stock, String startTime,String endTime,SelectValueBean open,SelectValueBean high,SelectValueBean low, SelectValueBean Close
//            ,   SelectValueBean adj_price,SelectValueBean volume,SelectValueBean pe,SelectValueBean pb);
    public void testGetStockInfoBySelect(){

        ArrayList<StockInfoVO> list=null;

       ArrayList<ArrayList<SelectValueBean>> beanList=new ArrayList<ArrayList<SelectValueBean>>();
       ArrayList<SelectValueBean> list1=new ArrayList<SelectValueBean>();
        list1.add(new SelectValueBean(8,9));
        list1.add(new SelectValueBean(8,9));
        list1.add(new SelectValueBean(8,9));
        list1.add(new SelectValueBean(8,9));
        list1.add(null);
        list1.add(null);
        list1.add(null);
        list1.add(null);
        beanList.add(list1);
       ArrayList<SelectValueBean> list2=new ArrayList<SelectValueBean>();
        list2.add(new SelectValueBean(10,9));
        list2.add(null);
        list2.add(null);
        list2.add(null);
        list2.add(null);
        list2.add(null);
        list2.add(null);
        list2.add(null);
        beanList.add(list2);
       ArrayList<SelectValueBean> list3=new ArrayList<SelectValueBean>();
        list3.add(null);
        list3.add(new SelectValueBean(0,7));
        list3.add(new SelectValueBean(8,9));
        list3.add(new SelectValueBean(8,10));
        list3.add(null);
        list3.add(null);
        list3.add(null);
        list3.add(null);
        beanList.add(list3);
       ArrayList<SelectValueBean> list4=new ArrayList<SelectValueBean>();
        list4.add(new SelectValueBean(11,9));
        list4.add(new SelectValueBean(8,9));
        list4.add(new SelectValueBean(2,9));
        list4.add(new SelectValueBean(20,30));
        list4.add(null);
        list4.add(null);
        list4.add(null);
        list4.add(null);
        beanList.add(list4);
       ArrayList<SelectValueBean> list5=new ArrayList<SelectValueBean>();
        list5.add(new SelectValueBean(20,9));
        list5.add(new SelectValueBean(20,50));
        list5.add(new SelectValueBean(30,9));
        list5.add(new SelectValueBean(8,9));
        list5.add(null);
        list5.add(null);
        list5.add(null);
        list5.add(null);
        beanList.add(list5);

        for(int i=0;i<startTime.size();i++){
            for(ArrayList<SelectValueBean> subList:beanList){
                list=stockInfoBLService.getStockInfoBySelect("sh600016",startTime.get(i),endTime.get(i),subList.get(0),subList.get(1),subList.get(2),subList.get(3),
                        subList.get(4),subList.get(5),subList.get(6),subList.get(7));
//                System.out.println(stockName);
                for(StockInfoVO vo:list){
                    System.out.println(vo.getDate()+" "+vo.getHigh());
                }
            }
        }

    }


    public void testGetKlineDate(){
//        String start, String end, String codeNum
        List<KlineVO> list=vs.getKlineDate("2016-01-10","2016-02-05","sh601988");
        for(int i=0;i<startTime.size();i++){
            for(int j=0;j<stockName.size();j++){
                list=vs.getKlineDate(startTime.get(i),endTime.get(i),stockName.get(j));
                for(KlineVO vo:list){
                    System.out.println(vo.getDay());
                }
            }
        }

    }
    public void  testGetHistoryInfo(){
        ArrayList<HistoryInfoVO> list=stockInfoBLService.getHistoryInfo("sh601988");
        for(int j=0;j<stockName.size();j++){
            list=stockInfoBLService.getHistoryInfo(stockName.get(j));
            for(HistoryInfoVO vo:list){
                System.out.println(vo.getDate());
            }
        }
    }

    public void testGetNowTimeStockVO(){
        NowTimeStockInfoVO vo=null;
        for(int j=0;j<stockName.size();j++){
            vo=stockInfoBLService.getNowTimeStockInfo(stockName.get(j));
            System.out.println(vo.getDate());

        }
    }

    public void testGetIncreasement(){
        ArrayList<IncreasementVO> list=null;
        for(int i=0;i<startTime.size();i++){
            for(int j=0;j<stockName.size();j++){
                list=stockInfoBLService.getStockInscreasment(startTime.get(i),endTime.get(i),stockName.get(j));
                for(IncreasementVO vo:list){
                    System.out.println(vo.getDate()+" "+(vo.getIncreasement()+""));
                }
            }
        }
    }

    public void testGetMA(){
        ArrayList<MAVO> list=null;
        ArrayList<Integer> days=new ArrayList<Integer>();
        days.add(5);
        days.add(10);
        days.add(20);
        days.add(30);
        days.add(60);
        for(int i=0;i<startTime.size();i++){
            for(int j=0;j<stockName.size();j++){
                for(int k=0;k<days.size();k++){
                    list=stockInfoBLService.getMA(days.get(k),startTime.get(i),endTime.get(i),stockName.get(j));
                    for(MAVO vo:list){
                        System.out.println(vo.getDate()+" "+vo.getValue());
                    }
                }
            }
        }
    }

    //    public ArrayList<macdVO> getMACDData(String startTime, String endTime, String codeNum, String type);
    public void testGetMACDData(){
        ArrayList<macdVO> list=null;
        ArrayList<String> types=new ArrayList<String>();
        types.add("DEA");
        types.add("DIF");
        types.add("MACD");

        for(int i=0;i<startTime.size();i++){
            for(int j=0;j<stockName.size();j++){
                for(int k=0;k<types.size();k++){
                    list=stockInfoBLService.getMACDData(startTime.get(i),endTime.get(i),stockName.get(j),types.get(k));
                    System.out.println(startTime.get(i)+" "+endTime.get(i)+" "+stockName.get(j)+" "+types.get(k));
                    for(macdVO vo:list){
                        System.out.println(vo.getDate()+" "+vo.getValue());
                    }
                }
            }
        }
    }
}
