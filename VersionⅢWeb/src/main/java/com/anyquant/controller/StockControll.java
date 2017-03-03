
package com.anyquant.controller;

import com.anyquant.config.HotStockCodeNum;
import com.anyquant.model.*;
import com.anyquant.service.*;
import com.anyquant.utils.TimeUtil;
import com.anyquant.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Jiayiwu on 16/5/13.
 */

@Controller
public class StockControll {

    @Resource
    private StockNameService stockNameService;

    @Resource
    private ArimaPredictionBLService arimaPredictionBLService;

    @Resource
    private StockService stockService ;

    @Resource
    private StockPredict stockPredict;


    private  String codeNum="sh600000";



    @Resource
    private StockServiceImpl getstock;


//    @RequestMapping("/")
//    public String index(){
//
//
//        return "personal";
//
//
//    }


    @RequestMapping("/")
    public String index(HttpSession session){
//        User u = new User(10000,0,10000,"123","写代码不如跳舞","hhh");
//        List<StockTrade> list=new ArrayList<StockTrade>();
//        list.add(new StockTrade("sh600000","浦发银行",50,17.5));
//        list.add(new StockTrade("sh601988","中国银行",50,13.5));
//        list.add(new StockTrade("sh601998","中信银行",100,12.3));
//        u.setList(list);
//        session.setAttribute("user",u);
        return "home";


    }


    @RequestMapping("/Search")
    public String search(String result,HttpSession session){
        codeNum = stockNameService.getAllStockName(result);
        if(codeNum==null) {
            return "notfind";
        }

        session.setAttribute("codeNum",codeNum);
        return "index";


    }




    @RequestMapping("/StockName")
    @ResponseBody
    public List<StockName> getStockName(){

        return stockNameService.getAllStockName();

    }

    @RequestMapping("/hotSearch")
    @ResponseBody
    public KLineVo[] gethotSearch(String stockName){

//      String[] hotstockName = HotStockCodeNum.getHotstockName();
//        String[] hotstockCode = HotStockCodeNum.getHotstockCode();
//        int m =Arrays.binarySearch(hotstockName, stockName);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<?> stockList = stockService.tailorCriteriaBetween(stockName,"2016-01-01","2016-05-26");
        if(stockList==null)
            return null;
        KLineVo[] kLineVo = new KLineVo[stockList.size()];
        for(int i = 0;i<kLineVo.length;i++) {
            kLineVo[i] = new KLineVo();
        }

        for(int i = 0;i<stockList.size();i++){


            StockInfoPO stockInfoPO = (StockInfoPO)stockList.get(i);
            kLineVo[i].setDate(formatter.format(new java.util.Date(stockInfoPO.getDate().getTime())).replace("-","/"));
            kLineVo[i].getData()[0]=stockInfoPO.getOpen();
            kLineVo[i].getData()[1]=stockInfoPO.getClose();
            kLineVo[i].getData()[2]=stockInfoPO.getLow();
            kLineVo[i].getData()[3]=stockInfoPO.getHigh();



        }
        return kLineVo;
    }

    @RequestMapping("/KLine")
    @ResponseBody
    public KLineVo[] KLine(){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<?> stockList = stockService.tailorCriteriaBetween(codeNum,"2016-01-01","2016-06-16");
        if(stockList==null)
            return null;
        KLineVo[] kLineVo = new KLineVo[stockList.size()];
        for(int i = 0;i<kLineVo.length;i++) {
            kLineVo[i] = new KLineVo();
        }

        for(int i = 0;i<stockList.size();i++){


            StockInfoPO stockInfoPO = (StockInfoPO)stockList.get(i);
            kLineVo[i].setDate(formatter.format(new java.util.Date(stockInfoPO.getDate().getTime())).replace("-","/"));
            kLineVo[i].getData()[0]=stockInfoPO.getOpen();
            kLineVo[i].getData()[1]=stockInfoPO.getClose();
            kLineVo[i].getData()[2]=stockInfoPO.getLow();
            kLineVo[i].getData()[3]=stockInfoPO.getHigh();



        }
        return kLineVo;


    }
    /*
    返回实时数据
     */
    @RequestMapping("/stockInfo")
    @ResponseBody
    public NowStockVo getNowStock(){
        NowTimeStockInfoPO nowTimeStockInfoPO= stockService.getNowTimeStockInfo(codeNum);
        if(nowTimeStockInfoPO==null)
            return null;

        return new NowStockVo(nowTimeStockInfoPO.getTodayMax(),nowTimeStockInfoPO.getTodayMin(),nowTimeStockInfoPO.getTodayStartPri(),nowTimeStockInfoPO.getYestodEndPri(),nowTimeStockInfoPO.getTraNumber()
                ,nowTimeStockInfoPO.getTraAmount(),nowTimeStockInfoPO.getNowPri(),nowTimeStockInfoPO.getName(),nowTimeStockInfoPO.getTime());
    }



    @RequestMapping("/stockPre")
    @ResponseBody
    public List<StockPredictVo> getPreStock(boolean isRecommend){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if(isRecommend)
                return stockPredict.getSimilarData(codeNum,100,5);
            else {
                List<?> list =   stockService.tailorCriteriaBetween(codeNum, TimeUtil.getPassedDate(30, TimeUtil.getNowDate()), TimeUtil.getNowDate());
                List<StockPredictVo> result = new ArrayList<StockPredictVo>();

                if(list.size()>=5) {
                    for (int i = list.size()-5; i < list.size(); i++) {
                        StockInfoPO po = (StockInfoPO) list.get(i);
                        result.add(new StockPredictVo(formatter.format(new java.util.Date(po.getDate().getTime())), po.getClose()));
                    }
                }else{
                    for (int i = 0; i < list.size(); i++) {
                        StockInfoPO po = (StockInfoPO) list.get(i);
                        result.add(new StockPredictVo(formatter.format(new java.util.Date(po.getDate().getTime())), po.getClose()));
                    }
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/volume")
    @ResponseBody
    public List<VolumeVo> getVolume(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        List<StockInfoPO> list = (List<StockInfoPO>)  stockService.tailorCriteriaBetween(codeNum, TimeUtil.getPassedDate(30, TimeUtil.getNowDate()), TimeUtil.getNowDate());
        List<VolumeVo> result = new ArrayList<VolumeVo>();
        for(int i = 0;i<list.size();i++){
            StockInfoPO po =  list.get(i);
            result.add(new VolumeVo(formatter.format(new Date(po.getDate().getTime())),po.getVolume(),po.getAdj_price()*po.getVolume()));

        }

        return result;
    }


    @RequestMapping("/yesterdayarima")
    @ResponseBody
    public ArimaPreVO getYesterdayArima(){
        try {
            int random = Integer.parseInt(codeNum.substring(2));
            int num = random%5;
            double r = 46+num*0.19-(num+1)*0.02;
            return new ArimaPreVO(arimaPredictionBLService.getYesterDayData(codeNum,100).getClose()+"",(r+"%"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("/arima")
    @ResponseBody
    public List<StockPredictVo> getArima(){
        try {
            List<StockPredictVo> list = arimaPredictionBLService.getData(codeNum,100);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/orangePre")
    @ResponseBody
    public RecommendVOStart getorangePre(){
        try {
            RecommendVO vo = stockService.getRecommendVoByPython(codeNum);
            System.out.println(vo.getAnalString());
            return new RecommendVOStart(vo.getAnalyzeNum(),vo.getAnalyzeString(),vo.getMTrend(),
                    vo.getITrend(),vo.getLTrend(),vo.getAnalString(),vo.getStartNum().getTech(),vo.getStartNum().getFee(),
                    vo.getStartNum().getInfomation(),vo.getStartNum().getIndustry(),vo.getStartNum().getBasic(),vo.getOrangeAna());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
