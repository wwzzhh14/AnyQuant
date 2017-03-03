package com.anyquant.controller;

import com.anyquant.model.StockTrade;
import com.anyquant.model.User;
import com.anyquant.service.TradeService;
import com.anyquant.utils.TimeUtil;
import com.anyquant.vo.MsgVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.anyquant.vo.Data;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Jiayiwu on 16/6/7.
 */
@Controller
public class TradeControll {


    @Resource
    TradeService tradeService;



    @RequestMapping(value = "/refreshStock",method = {RequestMethod.POST })
    @ResponseBody
    public MsgVo getAll( HttpSession session){

        User user =(User) session.getAttribute("user");

        if(user==null)
            return new MsgVo(false,"请登录后再进行操作",null);


        MsgVo tem = tradeService.refresh(user);
        return new MsgVo(true,"成功",tem.getUser());


    }



    @RequestMapping(value = "/trade",method = {RequestMethod.POST })
    @ResponseBody
    public MsgVo trade(@RequestBody List<LinkedHashMap> list, HttpSession session){

        boolean isOk = true;

        User user =(User) session.getAttribute("user");

        if(user==null)
            return new MsgVo(false,"请登录后再进行操作",null);
        if(list.size()==0){
            return new MsgVo(false,"请添加一只股票后再进行操作",null);
        }

        List<StockTrade> param = new ArrayList<StockTrade>();
        for(int i = 0;i<list.size();i++) {
            try {
                LinkedHashMap<String, String> t = list.get(i);
                StockTrade trade = new StockTrade(t.get("code"), t.get("name"), Integer.parseInt(t.get("num")), Double.parseDouble(t.get("price")));
                param.add(trade);
            }catch (Exception e){
                e.printStackTrace();
                isOk =false;
            }
        }

        if(isOk==false)
            return new MsgVo(false,"请正确填写交易数额",user);


        return tradeService.trade(user,param);


    }


    @RequestMapping(value = "/buy")
    @ResponseBody
    public MsgVo buy( HttpSession session){

        boolean isOk = true;

        User user =(User) session.getAttribute("user");
        String codeNum = (String) session.getAttribute("codeNum");
        if(user==null)
            return new MsgVo(false,"请登录后再进行操作",null);
        if(codeNum==null)
            return new MsgVo(false,"请选中某只股票后再进行操作",user);
        return tradeService.buy(user,codeNum);

    }


    @RequestMapping(value = "/webTest")
    @ResponseBody
    public List<Data> webHomework( ){
        Random r = new Random();
        List<Data> result = new ArrayList<Data>();
        double percent0=0;
        double percent1=0;
        double percent2=0;
        double percent3=0;
        double percent4=0;
        double sleep0 = 0;
        double sleep1 = 0;
        double sleep2 = 0;
        for(int i = 0;i<4;i++){

            percent0 = (r.nextInt(5)+40)/100.0;
            percent1 = r.nextInt(10)/100.0;
            percent2 = r.nextInt(5)/100.0;
            percent4 = r.nextInt(10)/100.0;
            percent3 = modify(1-percent0-percent1-percent2-percent4);
            sleep0 =(r.nextInt(5)+40)/100.0;
            sleep1 = (r.nextInt(5)+40)/100.0;
            sleep2 = modify(1- sleep0 -sleep1);


           result.add( new Data("08:00:20:0A:8C:6D","e1adc3949ba59abbe56e057f2f883e","1",
                   TimeUtil.getAddDate(i,"2016-11-28"),(50+r.nextInt(10)),(0.13+(r.nextInt(5)/100.0)),(2000+r.nextInt(2500)),(r.nextInt(10)+2),percent0,
                   percent1,percent2,percent3,percent4,((r.nextInt(10)/10.0+ r.nextInt(4))+5),sleep0,sleep1,sleep2));
        }
        return  result;

    }

    private double modify(double param){
        DecimalFormat    df   = new DecimalFormat("######0.00");
        return Double.parseDouble(df.format(param));

    }
}
