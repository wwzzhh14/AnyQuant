package businesslogic.predictionlogic;

import arima.ARIMA;
import businesslogicservice.predictionlogicservice.ArimaPredictionBLService;
import config.StockName;
import net.StockNetImpl;
import po.StockInfoPO;
import util.TimeUtil;
import vo.StockInfoVO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 2016/5/3.
 */
public class ArimaAlgorithmImp implements ArimaPredictionBLService {

    private final double thRate = 0.05;

    private ArrayList<ArrayList<Double>> dataList = new ArrayList<ArrayList<Double>>();
    ArrayList<StockInfoPO> poList;
    ArrayList<Double> currentDataList;
    public String date = TimeUtil.getPassedDate(0, TimeUtil.getNowDate());


    public ArrayList<StockInfoVO> getData(String stock, double days) throws Exception {

        initData(stock, days);
        double predict = makeModel();

        ArrayList<StockInfoPO> resultPOList = new StockNetImpl().getStockInfoByTime(stock, TimeUtil.getPassedDate(days, TimeUtil.getNowDate()), TimeUtil.getNowDate());
        StockInfoPO predictPrice = new StockInfoPO(0, 0, 0, 0, 0, predict, 0, 0, 0, TimeUtil.getPassedDate(-1, TimeUtil.getNowDate()));
        resultPOList.add(predictPrice);
        ArrayList<StockInfoVO> resultList = poToVO(resultPOList, stock);
        return resultList;

    }

    public void test(String stock, double days) {
        for (int j = 33;j>=0;j--){
            date = TimeUtil.getPassedDate(j, TimeUtil.getNowDate());
            initData(stock, days);

            double[] dataArray = new double[currentDataList.size() - 1];
//            if (currentDataList.size() == 429){
                for (int i = 0; i < currentDataList.size() - 1; i++) {
                    dataArray[i] = currentDataList.get(i);
//            System.out.println(dataArray[i]);
                }

                System.out.print(poList.get(poList.size() - 1).getDate() + " ");
                System.out.print(currentDataList.size() + " ");

                ARIMA arima = new ARIMA(dataArray);

                int[] model = arima.getARIMAmodel();
                DecimalFormat df3 = new DecimalFormat("##.000");
                double predictValue = arima.aftDeal(arima.predictValue(model[0], model[1]));
                double actualValue = currentDataList.get(currentDataList.size() - 1);
                double preValue = currentDataList.get(currentDataList.size() - 2);
//        System.out.println("Best model is [p,q]=" + "[" + model[0] + " " + model[1] + "]");
//        System.out.print(actualValue+" ");
                System.out.print(df3.format(predictValue)+" \n");
//        System.out.print(df3.format((predictValue - actualValue) / actualValue * 100) + "%\n ");
//        System.out.print(preValue+" ");
                double flag = (predictValue - preValue) * (actualValue - preValue);
//        System.out.print(flag + " ");
                if (flag < 0) {
//            System.out.print("N\n");
                } else {
//            System.out.print("Y\n");
                }
//        } else {
//            System.out.print("wow!\n");
//        }
//            }
        }


    }

    private void initData(String stock, double days) {
        poList = new ArrayList<StockInfoPO>();
        currentDataList = new ArrayList<Double>();
        poList = new StockNetImpl().getStockInfoByTime(stock, TimeUtil.getPassedDate(days, date), date);
        for (int i = 0; i < poList.size(); i++) {
            if(poList.get(i).getClose()== 0)
                continue;
            else
                currentDataList.add(poList.get(i).getClose());
//            System.out.println(poList.get(i).getDate()+"  "+poList.get(i).getClose());
        }
//        System.out.println(currentDataList.size());
    }

    private double makeModel() {
        double[] dataArray = new double[currentDataList.size()];
        for (int i = 0; i < currentDataList.size(); i++)
            dataArray[i] = currentDataList.get(i);

//        System.out.println(currentDataList.size());

        ARIMA arima = new ARIMA(dataArray);
        int[] model = arima.getARIMAmodel();
        return arima.aftDeal(arima.predictValue(model[0], model[1]));

//        System.out.println("Best model is [p,q]=" + "[" + model[0] + " " + model[1] + "]");
//        System.out.println("Predict value=" + arima.aftDeal(arima.predictValue(model[0], model[1])));
//        System.out.println("Predict error=" + (arima.aftDeal(arima.predictValue(model[0], model[1])) - currentDataList.get(currentDataList.size() - 1)) / currentDataList.get(arraylist.size() - 1) * 100 + "%");
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

}
