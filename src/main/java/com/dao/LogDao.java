package com.dao;

import com.entity.Log;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Vector;

import javax.inject.Named;

@Named
public class LogDao extends BaseDao<Log, Long> {

    public void batchSave_dao(Vector<Log> v) {
        Session session = getSession();
        try {

            for (int i = 0; i < v.size(); i++) {
                Log userLogger = v.get(i);
                session.save(userLogger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Log> getLogByUserId(Long userId, Integer first, Integer max) {
        Criteria c = getSession().createCriteria(Log.class);
        c.add(Restrictions.eq("userId", userId));
        c.addOrder(Order.desc("createDate"));
        c.setFirstResult(first);
        c.setMaxResults(max);
        return c.list();
    }

    public Integer getLogByUserId(Long userId) {
        Criteria c = getSession().createCriteria(Log.class);
        c.add(Restrictions.eq("userId", userId));
        return c.list().size();
    }

    public List<Log> getAllLogByPage(Integer first, Integer max) {
        Criteria c = getSession().createCriteria(Log.class);
        c.addOrder(Order.desc("createDate"));
        c.setFirstResult(first);
        c.setMaxResults(max);
        return c.list();
    }

    public Long getCount() {
        Criteria c = getSession().createCriteria(Log.class);
        long count = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
        return count;
    }


}
