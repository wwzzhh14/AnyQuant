package businesslogicservice.predictionlogicservice;

import vo.StockInfoVO;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by HP on 2016/5/5.
 */
public interface ArimaPredictionBLService {

    /*
    days:总参考天数
    type：price 0
       or volumn 1
     */
    public ArrayList<StockInfoVO> getData(String stock, double days) throws Exception;
    public void test(String stock,double days) throws Exception;
}
