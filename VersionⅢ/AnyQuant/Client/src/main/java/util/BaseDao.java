package util;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by Jiayiwu on 16/5/15.
 */
public class BaseDao {

    private Session getSession(){
        return Hibernate.getSessionFactory().openSession();
    }

    public void save(Object entity) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(entity);
            tx.commit();
            session.clear();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();

        } finally {
            session.close();
        }
    }
}
