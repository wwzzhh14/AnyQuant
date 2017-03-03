package com.anyquant.controller;

import com.anyquant.service.RecommendService;
import com.anyquant.utils.TimeUtil;
import com.anyquant.vo.HotStockVo;
import com.anyquant.vo.IndustryVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/31.
 */
@Controller
public class RecommendControll {
    @Resource
    RecommendService recommendService;

    @RequestMapping("/hotstock")
    @ResponseBody
    public List<HotStockVo> getHotStock(){

   return recommendService.getHotStock();


    }

    @RequestMapping("/recommendIndustry")
    @ResponseBody
    public List<IndustryVo> getIndustry(){

        List<IndustryVo> voList=new ArrayList<IndustryVo>();
        for(int i=0;i<8;i++){
            List<IndustryVo> subvoList=recommendService.getIndustry(TimeUtil.getPassedDate(7*i,TimeUtil.getNowDate()),7);
            for(IndustryVo vo:subvoList){
                voList.add(vo);
            }
        }
        Collections.reverse(voList);
        return voList;


    }
}
