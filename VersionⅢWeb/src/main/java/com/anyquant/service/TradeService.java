package com.anyquant.service;

import com.anyquant.model.StockTrade;
import com.anyquant.model.User;
import com.anyquant.vo.MsgVo;

import java.util.List;

/**
 * Created by Jiayiwu on 16/6/8.
 */
public interface TradeService {

    public MsgVo trade(User user, List<StockTrade> list);

    public MsgVo refresh(User user);

    public MsgVo buy(User user,String codeNum);
}
