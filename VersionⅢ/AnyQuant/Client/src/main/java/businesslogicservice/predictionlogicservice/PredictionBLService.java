package businesslogicservice.predictionlogicservice;

import vo.StockInfoVO;

import java.util.ArrayList;

/**
 * Created by HP on 2016/5/5.
 */
public interface PredictionBLService {

    /*
    days:总参考天数
    width：单位样本大小
     */
    public ArrayList<StockInfoVO> getSimilarData(String stock, double days, int width) throws Exception;
}
