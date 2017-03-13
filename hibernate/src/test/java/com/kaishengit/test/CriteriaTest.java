package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

public class CriteriaTest {

    @Test
    public void findAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Criteria criteria = session.createCriteria(User.class);
        List<User> userList = criteria.list();

        for(User user : userList) {
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void where() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Criteria criteria = session.createCriteria(User.class);

        criteria.addOrder(Order.desc("id"));

        //criteria.setFirstResult(2);
        //criteria.setMaxResults(5);

        //criteria.add(Restrictions.like("userName","i", MatchMode.ANYWHERE));


        //criteria.add(Restrictions.eq("userName","X1"));
        //criteria.add(Restrictions.eq("password","333444"));

       /* Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("userName","X1"));
        disjunction.add(Restrictions.eq("userName","X3"));

        criteria.add(disjunction);*/


        /*criteria.add(Restrictions.or(
                Restrictions.eq("userName","X1"),
                Restrictions.eq("userName","X3")));*/

        List<User> userList = criteria.list();

        for(User user : userList) {
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void count() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Criteria criteria = session.createCriteria(User.class);

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("id"));

        criteria.setProjection(projectionList);

        Object[] result = (Object[]) criteria.uniqueResult();
        System.out.println(result[0] + "  " + result[1]);

        session.getTransaction().commit();
    }
}
