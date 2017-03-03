package NetTestCase;

import config.StockName;
import junit.framework.TestCase;
import net.StockNetImpl;
import netservice.StockNetService;
import po.NowTimeStockInfoPO;
import po.StockInfoPO;
import stockutil.TimeUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HP on 2016/3/3.
 */
public class TestStockNetService extends TestCase{

    StockNetService testStockNet=new StockNetImpl();

    public void testGetStockByYearOrByPlace(){
//        ArrayList<String> result=testStockNet.getStockByYearOrByPlace("2009", ExchangePlace.sh);
//        for(String s:result){
//            System.out.println(s);
//        }

    }

    public void testGetStockInfo(){
        ArrayList<StockInfoPO> list=testStockNet.getStockInfo("sh600000");
        for(StockInfoPO po:list){
            System.out.println(po.getAdj_price()+" "+po.getClose()+" "+po.getHigh()+" "+po.getLow()+" "+po.getOpen()+" "+
            po.getPb()+" "+po.getPe_ttm()+" "+po.getTurnover()+" "+po.getVolume()+" "+po.getDate());
        }

    }

    public void testGetNowTimeStockInfo(){
        NowTimeStockInfoPO po=testStockNet.getNowTimeStockInfo("sh600000");
        System.out.println(po.getDate()+" "+po.getTime()+" "+po.getIncrePer());
    }
    public void testGetStockInfoByTime(){

    }

    public void testGetData(){
        BufferedWriter bw=getWriter("mydata/EMA12/ema12.txt",true);
        StringBuffer sb=new StringBuffer();
        Object[] name= StockName.getStockNameAndCode().keySet().toArray();
        for(int i=0;i<name.length;i++){
            ArrayList<StockInfoPO> list=testStockNet.getStockInfoByTime((String)name[i], TimeUtil.getPassedDate(12,"2016-03-29"),"2016-03-29");
            double sum=0;
            for(int j=0;j<list.size();j++){
                sum+=list.get(j).getClose();
            }
            double avg=sum/list.size();
            sb.append("2016-03-28/").append((String)name[i]+"/").append(avg+""+"\n");
        }
        sb.append("end");
        try {
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(list.get(list.size()-1).getDate());

    }
    private BufferedWriter getWriter(String path, boolean isCovered){
        File f=new File(path);
        BufferedWriter bw=null;
        try {
            FileWriter fw= new FileWriter(f,!isCovered);
            bw  = new BufferedWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bw;
    }
}
