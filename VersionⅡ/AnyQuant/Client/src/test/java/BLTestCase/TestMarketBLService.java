package BLTestCase;

import businesslogic.MarketBlLImpl;
import businesslogicservice.MarketBLService;
import junit.framework.TestCase;
import po.BenchMarkInfoPO;
import stockutil.TimeUtil;
import vo.*;

import java.util.ArrayList;

/**
 * Created by HP on 2016/3/7.
 */
public class TestMarketBLService extends TestCase{


   ArrayList<String> startTime=new ArrayList<String>();
   ArrayList<String> endTime=new ArrayList<String>();


    MarketBLService marketBLService=new MarketBlLImpl();
    public void setUp() {
        System.out.println("**************************");
    }
    public void testGetAllBenchMark(){
        ArrayList<String> list=marketBLService.getAllBenchMark();
        for(String s:list){
            System.out.println(s);
        }
    }
    //   用于返回某段时间内某大盘的详细信息。当前两个参数为null时，默认startTime=2016-01-01,endTime=2016-02-01
//    String startTime, String endTime, String benchmark
    public void testGetBenchMarkInfoByTimeOrByMarket(){

        ArrayList<BenchMarkInfoVO> list;
        startTime.clear();
        endTime.clear();

        startTime.add("2016-01-01");
        endTime.add("2016-01-01");

        startTime.add("2016-01-01");
        endTime.add("2016-04-01");

        startTime.add("2015-01-01");
        endTime.add("2016-01-01");

        startTime.add("2016-01-01");
        endTime.add("2016-05-01");

        startTime.add("2013-01-01");
        endTime.add("2016-01-01");


        for(int i=0;i<endTime.size();i++){
            System.out.println("start:"+startTime.get(i)+" end:"+endTime.get(i));
            list=marketBLService.getBenchMarkInfoByTimeOrByMarket(startTime.get(i),endTime.get(i),"hs300");
            for(BenchMarkInfoVO vo:list){
                System.out.println(vo.getDate());
            }
        }

    }

    public void  testGetHistoryInfo(){
        ArrayList<HistoryInfoVO> voList=marketBLService.getHistoryInfo("hs300");
        for(HistoryInfoVO vo:voList){
            System.out.println(vo.getDate()+"　"+vo.getVolume()+" "+vo.getPrice()+" "+vo.getSum()+" "+vo.isUp());
        }
    }

    public void testGetQuadrantDiagramInfo(){
        startTime.clear();
        endTime.clear();

        startTime.add("2017-01-01");
        startTime.add("2015-01-01");
        startTime.add("2013-01-01");

        ArrayList<Integer> days=new ArrayList();
        days.add(1);
        days.add(2);
        days.add(5);
        days.add(10);
        days.add(50);
        days.add(100);

        ArrayList<QuadrantDiagramVO> voList=null;

        for(int d:days){
            for(String t:startTime){
                System.out.println("time:"+t+" days:"+d);
                voList=marketBLService.getQuadrantDiagramInfo(t,d);

                for(QuadrantDiagramVO vo:voList){
                    System.out.println(vo.getName()+" "+vo.getPe()+" "+vo.getSum());
                }
            }
        }
//        MarketBLService m1=new MarketBlLImpl();
//        MarketBLService m2=new MarketBlLImpl();
//
//        ArrayList<QuadrantDiagramVO> voList1=m1.getQuadrantDiagramInfo(TimeUtil.getNowDate(),10);
//        ArrayList<QuadrantDiagramVO> voList2=m2.getQuadrantDiagramInfo();
//
//        for(QuadrantDiagramVO vo:voList1){
//                    System.out.println(vo.getName()+" "+vo.getPe()+" "+vo.getSum());
//                }
//        System.out.println("***********");
//        for(QuadrantDiagramVO vo:voList2){
//                    System.out.println(vo.getName()+" "+vo.getPe()+" "+vo.getSum());
//                }

    }
    public void   testGetMartketInscreasment(){
        startTime.clear();
        endTime.clear();

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

        ArrayList<IncreasementVO> list=null;
        for(int i=0;i<startTime.size();i++){
            list=marketBLService.getMartketInscreasment(startTime.get(i),endTime.get(i),"hs300");
            System.out.println("start:"+startTime.get(i)+"end:"+endTime.get(i));
            for(IncreasementVO vo:list){
                System.out.println(vo.getDate()+" "+vo.getIncreasement());
            }
        }

    }
    public void testGetMA(){
        startTime.clear();
        endTime.clear();

        startTime.add("2016-01-01");
        endTime.add("2016-01-01");

        startTime.add("2016-01-01");
        endTime.add("2016-04-01");

        startTime.add("2016-01-01");
        endTime.add("2015-01-01");

        startTime.add("2016-01-01");
        endTime.add("2016-05-01");

        startTime.add("2012-01-01");
        endTime.add("2016-01-01");

        ArrayList<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(10);
        list.add(30);
        list.add(60);

        ArrayList<MAVO> voList=null;

        for(int d:list){
            for (int i=0;i<startTime.size();i++){
                System.out.println("start:"+startTime.get(i)+" end:"+endTime.get(i)+" d:"+d);
                voList=marketBLService.getMA(d,startTime.get(i),endTime.get(i),"hs300");
                for (MAVO vo:voList){
                    System.out.println(vo.getDate()+" "+vo.getValue());
                }
            }
        }

    }

}
