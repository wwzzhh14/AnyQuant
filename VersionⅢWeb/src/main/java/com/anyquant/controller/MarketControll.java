package com.anyquant.controller;

import com.anyquant.model.BenchMarkInfoPO;
import com.anyquant.model.NowTimeBenchMarkInfoPO;
import com.anyquant.model.StockName;
import com.anyquant.service.MarketService;
import com.anyquant.utils.TimeUtil;
import com.anyquant.vo.BenchMarkInfoVo;
import com.anyquant.vo.KLineVo;
import com.anyquant.vo.MarkHomeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/31.
 */
@Controller
public class MarketControll {

    @Resource
    MarketService marketService;

    @RequestMapping("/markHome")
    @ResponseBody
    public MarkHomeVo getStockName(){

    return marketService.getMarketHome();

    }

    @RequestMapping("/historyMarketDate")
    @ResponseBody

    public List<BenchMarkInfoVo> gethistoryMarket(){
        List<BenchMarkInfoVo>result = new ArrayList<BenchMarkInfoVo>();
        List<BenchMarkInfoPO>list= marketService.getBenchMarkInfo(TimeUtil.getPassedDate(60,TimeUtil.getNowDate()),TimeUtil.getNowDate());
        for (int i = 0;i<list.size();i++){
            result.add(new BenchMarkInfoVo(list.get(i)));
        }
        return result;

    }

    @RequestMapping("/MarketNow")
    @ResponseBody
    public NowTimeBenchMarkInfoPO getnowMarket(){

        return marketService.getnowMatket();
    }

    @RequestMapping("/marketKLine")
    @ResponseBody
    public KLineVo[] getMarketKLine(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<BenchMarkInfoPO>list = marketService.getBenchMarkInfo(TimeUtil.getPassedDate(60,TimeUtil.getNowDate()),TimeUtil.getNowDate());
        KLineVo[] result = new KLineVo[list.size()];
        for(int i = 0;i<list.size();i++){
            BenchMarkInfoPO tem = list.get(i);
            result[i]=new KLineVo(formatter.format(tem.getDate()),tem.getOpen(),tem.getClose(),tem.getHigh(),tem.getLow());
        }
        return result;
    }
}
