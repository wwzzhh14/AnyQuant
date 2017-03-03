package com.anyquant.dao;

import com.anyquant.config.State;
import com.anyquant.config.StringMessage;
import com.anyquant.model.BenchMarkInfoPO;
import com.anyquant.model.NowTimeBenchMarkInfoPO;
import com.anyquant.service.MarketService;
import com.anyquant.utils.JsonAnalysisUtil;
import com.anyquant.utils.JsonDataUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/31.
 */
@Repository
public class MarketDao {
    @Resource
    private SessionFactory sessionFactory;
    private Session getSession() {
        return sessionFactory.openSession();
    }
    private JsonDataUtil jsonDataUtil= JsonDataUtil.instance();
    public List<BenchMarkInfoPO> getBenchMarkInfo(String start,String end){
        List<BenchMarkInfoPO> list = null;
        Session session = getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(BenchMarkInfoPO.class).add(Restrictions.between("date",util2sql(start),util2sql(end)));
            list = criteria.list();
            session.getTransaction().commit();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            if (session!=null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return list;
    }

    public NowTimeBenchMarkInfoPO getNowtimeBnech() {
        StringMessage stringMessage;
        stringMessage=jsonDataUtil.getNowTimeMarketResult();
        if(stringMessage.getResult()== State.OK){
            NowTimeBenchMarkInfoPO po= JsonAnalysisUtil.json2Bean(stringMessage.getData(), NowTimeBenchMarkInfoPO.class);
            return po;
        }
        return null;
    }

    private java.util.Date util2sql(String date){
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        try {
            return  fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
}
