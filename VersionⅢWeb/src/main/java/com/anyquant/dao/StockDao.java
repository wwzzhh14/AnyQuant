package com.anyquant.dao;

import com.anyquant.config.Msg;
import com.anyquant.model.StockInfoPO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Jiayiwu on 16/5/15.
 */
@Repository
public class StockDao {
    @Resource
    private SessionFactory sessionFactory;
    private Session getSession() {
        return sessionFactory.openSession();
    }
    @Resource
    private BaseDao baseDao;


    public Msg save(StockInfoPO stockInfoPO){
       return baseDao.save(stockInfoPO);
    }


    public List<?> getStockLength(int up ,int num){
        return baseDao.getPageAll(StockInfoPO.class,up,num);
    }

    public List<?> getAllStockLength(){
        return baseDao.getAll(StockInfoPO.class);
    }


    public List<?> tailorCriteriaBetween(Class<?> className,String codeNum,String start,String end ) {
        List<?> list = null;
        Session session = getSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(className).add(Restrictions.between("date",util2sql(start),util2sql(end))).add(Restrictions.eq("codeNum",codeNum));
//             Criteria criteria = session.createCriteria(className).add(Restrictions.between("date",start,end)).add(Restrictions.eq("codeNum",codeNum));
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
