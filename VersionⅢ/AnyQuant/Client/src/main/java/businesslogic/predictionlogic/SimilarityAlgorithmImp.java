package businesslogic.predictionlogic;

import businesslogicservice.predictionlogicservice.PredictionBLService;
import config.StockName;
import net.StockNetImpl;
import po.StockInfoPO;
import util.TimeUtil;
import vo.StockInfoVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HP on 2016/5/3.
 */
public class SimilarityAlgorithmImp implements PredictionBLService {

    private final double thRate=0.05;

    private ArrayList<ArrayList<Double>> dataList=new ArrayList<ArrayList<Double>>();
    ArrayList<StockInfoPO> poList;
    ArrayList<Double> currentDataList=new ArrayList<Double>();


    public ArrayList<StockInfoVO> getSimilarData(String stock, double days, int width) throws Exception {
        if(width>days){
            throw new Exception("width is longer than days");
        }
        initData(stock,days,width);

        ArrayList<PLR> currentPLRList= initPLRList(currentDataList);

        ArrayList<Integer> currentTimeArrayList=getTimeArray(currentPLRList,currentDataList);

        int index=0;
        ArrayList<Double> valueList1=dataList.get(0);
        ArrayList<PLR> prlList1= initPLRList(valueList1);
        ArrayList<Integer> timeArrayList1=getTimeArray(prlList1,valueList1);
        double length=calLength(currentPLRList,prlList1,currentTimeArrayList,timeArrayList1);
//        System.out.println(length);

        for(int i=1;i<dataList.size();i++){
            ArrayList<Double> valueList=dataList.get(i);
            ArrayList<PLR> prlList= initPLRList(valueList);
            ArrayList<Integer> timeArrayList=getTimeArray(prlList,valueList);
            double myLenth=calLength(currentPLRList,prlList,currentTimeArrayList,timeArrayList);
           if(myLenth<length){
               length=myLenth;
               index=i;
           }

        }

        String date=poList.get(index).getDate();
        ArrayList<StockInfoPO> resultPOList=new StockNetImpl().getStockInfoByTime(stock, date,TimeUtil.getPassedDate(-(width+7),date));
        ArrayList<StockInfoVO> resultList=poToVO(resultPOList,stock);
        return resultList;

    }

    private void initData(String stock,double days,int width){
        poList=new StockNetImpl().getStockInfoByTime(stock, TimeUtil.getPassedDate(days+width,TimeUtil.getNowDate()),TimeUtil.getNowDate());
        for(int i=poList.size()-width;i<poList.size();i++){
            currentDataList.add(poList.get(i).getClose());
        }
        for(int i=0;i<poList.size()-2*width;i++){
            ArrayList<Double> closeList=new ArrayList<Double>();
            for(int j=i;j<i+width;j++){
                closeList.add(poList.get(j).getClose());
            }
            dataList.add(closeList);
        }
    }

    private ArrayList<PLR> initPLRList(ArrayList<Double> valueList){
        ArrayList<PLR> plrList=new ArrayList<PLR>();
        for(int i=0;i<valueList.size()-1;i++){
          plrList.add(new PLR(valueList.get(i),valueList.get(i+1),1+i));
        }

        return plrList;
    }

    private ArrayList<Integer> getTimeArray(ArrayList<PLR> plrList,ArrayList<Double> valueList){

        ArrayList<Integer> resultList=new ArrayList<Integer>();
        ArrayList<Double> kList=new ArrayList<Double>();
        double maxItem=max(valueList);
        double minItem=min(valueList);
        double th=average(valueList)*thRate/(maxItem-minItem);
//        double th=average(valueList)*0.1;
        for(PLR plr:plrList){
            plr.value1=(plr.value1-minItem)/(maxItem-minItem);
            plr.value2=(plr.value2-minItem)/(maxItem-minItem);
        }

        PLR plr=plrList.get(0);
        double k=plr.value2-plr.value1/1;
        kList.add(k);
        if(k<-th){
            resultList.add(3);
        }else if(k>th){
            resultList.add(-3);
        }else{
            resultList.add(0);
        }


        for(int i=1;i<plrList.size();i++){

            plr=plrList.get(i);
            double k1=kList.get(i-1);
            double k2=(plr.value2-plr.value1)/1;
            kList.add(k2);
            if((k1<-th||(k1>-th&&k1>th))&&k2<k1){
                resultList.add(-3);
                continue;
            }
            if(k1<-th&&k1==k2){
                resultList.add(-2);
                continue;
            }
            if(k1<-th&&(k1<k2&&k2<-th)){
                resultList.add(-1);
                continue;
            }
            if(k1>th&&(k2>0&&k1>k2)){
                resultList.add(1);
                continue;
            }
            if(k1>th&&k1==k2){
                resultList.add(2);
                continue;
            }
            if((k1>th||(k1>-th&&k1<th))&&k2>k1){
                resultList.add(3);
                continue;
            }

            resultList.add(0);

        }

        return resultList;
    }

    private double calLength(ArrayList<PLR> plrList1,ArrayList<PLR> plrList2,ArrayList<Integer> mList1,ArrayList<Integer> mList2){
        double result=0;
        double sum=0;
        for(int i=0;i<plrList1.size();i++){
            sum+=(Math.abs((plrList1.get(i).value2-plrList1.get(i).value1)-(plrList2.get(i).value2-plrList2.get(i).value1))*Math.abs(mList1.get(i)-mList2.get(i)));
        }
        result=sum/plrList1.size();
        return result;
    }

    private <T extends Comparable> T max(List<T> list){
        T result=null;
        if(list.size()>0){
           result=list.get(0);
        }
        for(T item :list){
            if(item.compareTo(result)>0){
                result=item;
            }
        }
        return result;
    }
    private <T extends Comparable> T min(List<T> list){
        T result=null;
        if(list.size()>0){
           result=list.get(0);
        }
        for(T item :list){
            if(item.compareTo(result)<0){
                result=item;
            }
        }
        return result;
    }
    private double average(List<Double> list){
        double sum=0;

        for(double item :list){
            sum+=item;
        }
        return sum/list.size();
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

    private class PLR{
        double value1;
        double value2;
        double t;

        public PLR(double value1, double value2, double t) {
            this.value1 = value1;
            this.value2 = value2;
            this.t = t;
        }
    }
}
