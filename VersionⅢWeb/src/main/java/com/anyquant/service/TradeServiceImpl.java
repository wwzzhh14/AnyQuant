package com.anyquant.service;

import com.anyquant.dao.BaseDao;
import com.anyquant.model.NowTimeStockInfoPO;
import com.anyquant.model.StockTrade;
import com.anyquant.model.User;
import com.anyquant.vo.MsgVo;
import com.anyquant.vo.NowStockVo;
import org.springframework.binding.format.IntegerNumberFormatFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiayiwu on 16/6/8.
 */
@Service
public class TradeServiceImpl implements TradeService {

    @Resource
    StockService stockService;
    @Resource
    BaseDao baseDao;

    public MsgVo trade(User user, List<StockTrade> list) {


        double stock=0;
        double cash = user.getCash();
        List<StockTrade> nowList = user.getList();
        for(int i = 0;i<nowList.size();i++){
            StockTrade old = nowList.get(i);
            for(int j = 0;j<list.size();j++){
                StockTrade news= list.get(j);

                if(old.getCodeNum().equals(news.getCodeNum())){

                    int distance = old.getNum()-news.getNum();
                    double nowPrice = stockService.getNowTimeStockInfo(old.getCodeNum()).getNowPri();
                    list.get(j).setPrice(nowPrice);
                    BigDecimal b1 = new BigDecimal(nowPrice+"");
                    BigDecimal b2 = new BigDecimal(news.getNum()+"");
                    BigDecimal b3 = new BigDecimal(distance+"");
                    stock+=b1.multiply(b2).doubleValue();
                    cash+=b1.multiply(b3).doubleValue();

                }


            }
        }

        if(cash<0)
            return new MsgVo(false,"资金不足",user);



        for(int i = 0;i<list.size();i++){
            StockTrade s = list.get(i);
            if(s.getNum()==0) {
                list.remove(s);
                i--;
            }
            if(s.getNum()<0){
                return new MsgVo(false,"请正确填写交易数量",user);
            }
        }


        user.setCash(cash);
        user.setList(list);
        user.setStockPrice(stock);
        user.setTotal(cash+stock);

        baseDao.update(user);

        return new MsgVo(true,"交易成功",user);
    }


    public MsgVo refresh(User user) {
        double cash = user.getCash();
        List<StockTrade> list = user.getList();
        double stockValue=0;
        double price=0;
        for (int i = 0;i<list.size();i++){
            StockTrade stockTrade = list.get(i);
           price = stockService.getNowTimeStockInfo(stockTrade.getCodeNum()).getNowPri();
            if(price!=0) {
                user.getList().get(i).setPrice(price);
                stockValue += new BigDecimal(price+"").multiply(new BigDecimal(stockTrade.getNum()+"")).doubleValue();
            }else {
                stockValue+=new BigDecimal(stockTrade.getPrice()+"").multiply(new BigDecimal(stockTrade.getNum()+"")).doubleValue();
            }
        }
        user.setTotal(new BigDecimal(stockValue+"").add(new BigDecimal(cash+"")).doubleValue());
        user.setCash(cash);
        user.setStockPrice(stockValue);
        baseDao.update(user);
        return new MsgVo(true,"success",user);
    }

    public MsgVo buy(User user, String codeNum) {

        List<StockTrade> list = user.getList();
        for(int i = 0;i<list.size();i++){
            StockTrade s = list.get(i);
            if(s.getCodeNum().equals(codeNum)){
                return  new MsgVo(false,"该股票已经购买过,请到个人主页进行大批量购买",user);
            }
        }
        refresh(user);
        NowTimeStockInfoPO po = stockService.getNowTimeStockInfo(codeNum);
        double price  = po.getNowPri();
        if(user.getCash()<price) {
            return new MsgVo(false, "余额不足,购买失败", user);
        }else{
            user.setCash(new BigDecimal(user.getCash()+"").subtract(new BigDecimal(price+"")).doubleValue());
            user.getList().add(new StockTrade(codeNum,po.getName(),1,price));
            baseDao.update(user);
            return new MsgVo(true, "购买成功", user);
        }

    }
}
