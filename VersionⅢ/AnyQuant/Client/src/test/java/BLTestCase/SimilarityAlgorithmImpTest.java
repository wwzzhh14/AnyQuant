package BLTestCase;

import businesslogic.StockInfoBLImpl;
import businesslogic.predictionlogic.SimilarityAlgorithmImp;

import vo.StockInfoVO;

import java.util.ArrayList;

/**
 * Created by HP on 2016/5/5.
 */
public class SimilarityAlgorithmImpTest {

    SimilarityAlgorithmImp sai=new SimilarityAlgorithmImp();
    StockInfoBLImpl sii=new StockInfoBLImpl();

    public void testSA(){
//        try {
//            ArrayList<StockInfoVO> voList1=sai.getSimilarData("sh601288",60,5);
////            ArrayList<StockInfoVO> voList2=sii.getStockInfoByTime("sh600000", TimeUtil.getPassedDate(5,TimeUtil.getNowDate()),TimeUtil.getNowDate());
//
//            for(StockInfoVO vo:voList1){
//                System.out.print(vo.getClose()+" ");
//            }
//            System.out.println();
//
//
//
//        } catch (Exception e) {
//            System.out.print("!");
//        }


    }
}
