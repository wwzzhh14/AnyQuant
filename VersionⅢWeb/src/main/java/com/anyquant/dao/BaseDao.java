package com.anyquant.dao;


import com.anyquant.config.Msg;
import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
@Repository
public class BaseDao {
	private static final Logger log = LoggerFactory.getLogger(BaseDao.class);

	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public Msg save(Object entity) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(entity);
			tx.commit();
			session.clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return Msg.FAIL;
		} finally {
			session.close();
		}
	}

	public Msg update(Object entity) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();
			session.clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			return Msg.FAIL;
		} finally {
			session.close();
		}

	}

	public Msg delete(Object entity) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
			session.clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			return Msg.FAIL;
		} finally {
			session.close();
		}
	}

	public Msg deleteById(Class<?> className, int id) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Object instance = session.get(className, id);
			session.delete(instance);
			session.getTransaction().commit();
			session.clear();
			return Msg.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
			return Msg.FAIL;
		} finally {
			session.close();
		}
	}


	public List<?> getPageAll(Class<?> className, int up, int size) {
		List<?> list = null;
		Session session = getSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(className);
			criteria.addOrder(Order.desc("id"));
			criteria.setFirstResult(up);
			criteria.setMaxResults(size);
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
	public List<?> getAll(Class<?> className) {
		List<?> list = null;
		Session session = getSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(className);
			criteria.addOrder(Order.desc("id"));
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




	public Object findById(Class<?> className, int id) {
		Session session = getSession();
		try {
			Object instance = session.get(className, id);
			return instance;
		} catch (Exception re) {
			re.printStackTrace();
		return null;
		} finally {
			session.close();
		}
	}

	public List<?> findByExample(Object entity) {
		List<?> list = null;
		Session session = getSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(entity.getClass());
			criteria.addOrder(Order.desc("id"));
			criteria.add(Example.create(entity).enableLike(MatchMode.ANYWHERE));
			list = criteria.list();
			session.getTransaction().commit();
			// List<?> list = getSession().createQuery(
			// "from  " + className.getSimpleName()).list();
			session.clear();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return list;
	}

	public List<?> findByProperty(Class<?> className, String propertyName,
			Object value) {
		log.debug("finding Room instance with property: " + propertyName
				+ ", value: " + value);
		Session session = getSession();
		try {
			String queryString = "from " + className.getSimpleName()
					+ " as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List<?> list = queryObject.list();
			return list;
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public List<Object[]> execSqlQuery(String sql) {
		Session session = getSession();
		try {
			session.beginTransaction();
			List<Object[]> objects = session.createSQLQuery(sql).list();
			session.getTransaction().commit();
			session.clear();
			return objects;
		} catch (Exception re) {
			re.printStackTrace();
			if (session!=null) {
				session.getTransaction().rollback();
			}
		return null;
		} finally {
			session.close();
		}
	}
	
	public int clearTable(String myTable){
	    String hql = String.format("delete from %s",myTable);
		Session session = getSession();
	    Query query = session.createQuery(hql);
		int res = query.executeUpdate();
		session.close();
	    return res;
	}





}
