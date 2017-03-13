package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class HQLTest {

    @Test
    public void where() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        String hql = "from User where userName = :name and password = :pwd";
        Query query = session.createQuery(hql);
        query.setParameter("name","X1");
        query.setParameter("pwd","123123");

        query.setFirstResult(2);
        query.setMaxResults(2);

        //query.setString(0,"X1");

        List<User> userList = query.list();

        for(User user : userList) {
            System.out.println(user);
        }
        session.getTransaction().commit();
    }

    @Test
    public void queryProperty() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        String hql = "select id,userName,password from User";
        Query query = session.createQuery(hql);

        List<Object[]> nameList = query.list();

        for(Object[] obj : nameList) {
            System.out.println(obj[0] + "->" + obj[1] + "->" + obj[2]);
        }
        session.getTransaction().commit();
    }

    @Test
    public void count() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        String hql = "select count(*) from User";
        Query query = session.createQuery(hql);
        Long count = (Long) query.uniqueResult();
        System.out.println(count);

        session.getTransaction().commit();
    }

}
