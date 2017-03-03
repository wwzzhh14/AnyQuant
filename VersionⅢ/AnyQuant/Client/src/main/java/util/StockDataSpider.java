package util;

import net.Demo;
import net.StockNetImpl;
import netservice.StockNetService;
import po.StockInfoPO;
import vo.StockInfoVO;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jiayiwu on 16/5/23.
 */
public class StockDataSpider {
    private static int count=0;
    private static  StockNetService stockNet = new StockNetImpl();
    static StockDataSpider single = null;
   static BaseDao baseDao = new BaseDao();
    private StockDataSpider() {

    }

    public static StockDataSpider instance() {
        if (null == single)
            single = new StockDataSpider();
        return single;
    }


    public static  void refresh(){
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        String line = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("mydata/stockName.txt"));

            while ((line=br.readLine())!=null ){
                DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
                ArrayList<StockInfoPO> list =  getStockInfoByTime(line,"2016-05-20",date);
                if(list==null){
                    System.out.println("this is null"+line);
                    continue;
                }
//                for(int i = 0;i<list.size();i++){
//                    StockInfoPO po = list.get(i);
//                    MyDemo.wjy.demo.StockInfoPO stock = new MyDemo.wjy.demo.StockInfoPO(line,po.getPb(),po.getPe_ttm(),po.getTurnover(),po.getVolume(),
//                    po.getAdj_price(),po.getClose(),po.getLow(),po.getHigh(),po.getOpen(),fmt.parse(po.getDate()));
//                    baseDao.save(stock);
//                    count++;
//                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(line);
//            System.out.println(count);
        }
//        System.out.println(count);
    }

    private static  ArrayList<StockInfoPO> getStockInfoByTime(String stock, String startTime, String endTime) {



        ArrayList<StockInfoPO> poList = stockNet.getStockInfoByTime(stock, startTime, endTime);
        if (poList != null) {


            Collections.reverse(poList);
            return poList;
        }
        return null;
    }
    private static String getDate(){
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        File file = new File("mydata/refresh.log");
        if(!file.exists()) {

            return date;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("mydata/refresh.log"));

            String nowDay =br.readLine();

            if(!(nowDay.equals(date))){

                return nowDay;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,
                    "文件配置文件错误,请检查路径或重新导入程序", "系统信息", JOptionPane.ERROR_MESSAGE);
        }
        return date;
    }
}
