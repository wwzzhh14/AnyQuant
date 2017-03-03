package org.Client;

import BLTestCase.TestStockBLService;

/**
 * Unit test for simple App.
 */
public class AppTest
{
      public static void main( String[] args ){
//	  JsonDataUtil tem = JsonDataUtil.instance();
//      tem.getAllStocks(2014, ExchangePlace.sh).getData();
//      System.out.println(tem.getAllStocks(2014, ExchangePlace.sh).getData());
//      System.out.println(tem.getStock("2016-01-08", "2016-01-19", "sz002644", null).getData());
//      System.out.println(tem.getBenchmark(null).getData());
//          new TestLoginNetService().TestConnect();
//          new TestStockNetService().testGetStockInfo();
//          new TestMarketNetService().testGetBenchMarkInfoByTimeOrByMarket();
//          JsonDataUtil jsonDataUtil=JsonDataUtil.instance();
//          ArrayList<StockBasicInfoPO> resultList=new ArrayList<StockBasicInfoPO>();
//          StringMessage stringMessage=jsonDataUtil.getAllStocks(Integer.parseInt("2015"), ExchangePlace.sh);
//          System.out.println(stringMessage.getData());
//          new TestLoginBLService().testLogin();
//          TestMarketBLService tm=new TestMarketBLService();
//          tm.testGetAllBenchMark();
//          tm.testGetBenchMarkInfoByTimeOrByMarket();
          TestStockBLService ts=new TestStockBLService();
          ts.testGetStockByYearOrByPlace();
          ts.testGetStockInfo();
          ts.testGetStockInfoByTime();
	}
}
