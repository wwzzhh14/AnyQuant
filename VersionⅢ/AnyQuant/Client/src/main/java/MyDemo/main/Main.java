package MyDemo.main;


import businesslogic.StockInfoBLImpl;
import util.PythonSpider;
import vo.StockInfoVO;
import util.BaseDao;

import java.util.List;
import java.util.Random;


/**
 * Created by HP on 2016/3/2.
 */
public class Main {
   static int y =0;
    static int x =5;
     Object [] tem;
    public static void main(String[] args) {

//        BaseDao dao = new BaseDao();
//        StockInfoBLImpl stockInfoBL = new StockInfoBLImpl();
//        String[] bArray = {"sh601166","sh600036","sh600015", "sh600016", "sh601939", "sh601818","sh601009", "sh601328","sh601988", "sh601998","sh601169","sh600000", "sh601398", "sh601288"};
//
//        for (int i =0;i<bArray.length;i++){
//            List<StockInfoVO>stockInfoPOs =stockInfoBL.getStockInfo(bArray[i]);
//            for(int j = 0;j<stockInfoPOs.size();j++){
//                dao.save(stockInfoPOs.get(j));
//
//            }
//
//        }
//        JsonDataUtil j = JsonDataUtil.instance();
//        System.out.println(j.getStock("2016-02-11","2016-03-12","sh600000",null).getData());
//        int m=0;
//       m =  (x>=3)?(y=x*2+1):((x<=-1)?(y=x*2-1):(y=x*2));
//        System.out.println(y);
//        System.out.println(m);
////
////        MainFrame frame = new MainFrame();
////        frame.setVisible(true);

        PythonSpider.initPython();

    }

    private void test(){
        Random a = new Random();
        tem = new Object[a.nextInt(10)];
    }
}
