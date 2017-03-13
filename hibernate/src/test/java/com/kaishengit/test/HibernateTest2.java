package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;

public class HibernateTest2 {

    @Test
    public void getAndLoad() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.load(User.class,92);
        System.out.println(user.getUserName());

        session.getTransaction().commit();
    }

    @Test
    public void saveAndPersist() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = new User();
        user.setUserName("X1");
        user.setPassword("123123");

        session.persist(user);
        System.out.println(user.getId());
        /*Integer id = (Integer) session.save(user);
        System.out.println(id);*/


        session.getTransaction().commit();
    }

    @Test
    public void saveAndUpdate() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = new User();
        user.setUserName("X1");
        user.setPassword("123123");

        session.save(user);
        session.getTransaction().commit();

        Session session2 = HibernateUtil.getSession();
        session2.getTransaction().begin();

        user.setUserName("X3");
        session2.update(user);

        session2.getTransaction().commit();

    }

    @Test
    public void saveOrUpdate() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = new User();
        user.setUserName("X1");
        user.setPassword("123123");

        session.saveOrUpdate(user);
        session.getTransaction().commit();

        Session session2 = HibernateUtil.getSession();
        session2.getTransaction().begin();

        user.setUserName("X3");
        session2.saveOrUpdate(user);

        session2.getTransaction().commit();
    }

    @Test
    public void merge() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = new User();
        user.setUserName("X1");
        user.setPassword("123123");

        session.save(user);
        session.getTransaction().commit();

        Session session2 = HibernateUtil.getSession();
        session2.getTransaction().begin();

        user.setUserName("X3");
        session2.merge(user);

        session2.getTransaction().commit();
    }

    @Test
    public void clearAndFlush() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,92);

        user.setUserName("XX");
        session.flush();//立即同步对象的变化到数据库中



        session.getTransaction().commit();
    }

}
