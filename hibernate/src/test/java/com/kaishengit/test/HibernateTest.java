package com.kaishengit.test;

import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;

public class HibernateTest {

    @Test
    public void save() {
        //1.创建SessionFactory
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        //2.创建Session
        Session session = sessionFactory.getCurrentSession();
        //3.事务
        Transaction transaction = session.getTransaction();
        transaction.begin();

        /*User user = new User();
        user.setUserName("Hibernate");
        user.setPassword("4.3.11");

        session.save(user);*/

        Task task = new Task();
        task.setTitle("ZZZ");

        session.save(task);




        //4.结束
        transaction.commit();
        //session.close();


    }

    @Test
    public void findById() throws InterruptedException {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Task task = (Task) session.get(Task.class,"4028815e5acbca48015acbca4d370000",LockMode.UPGRADE);
        task.setTitle("xxxx");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Session session2 = HibernateUtil.getSession();
                session2.getTransaction().begin();
                Task task2 = (Task) session2.get(Task.class,"4028815e5acbca48015acbca4d370000");
                task2.setTitle("zzzzzz");
                session2.getTransaction().commit();
            }
        });

        thread.start();
        Thread.sleep(3000);
        session.getTransaction().commit();

       /* User user = (User) session.get(User.class,92);
        System.out.println(user);*/

        //System.out.println(session.contains(user));
        //session.clear();
        //session.evict(user);




        //Cache cache = HibernateUtil.getSessionFactory().getCache();
        //cache.evictAllRegions();
        //cache.evictEntityRegion(User.class);

       /* Session session1 = HibernateUtil.getSession();
        session1.getTransaction().begin();

        user = (User) session1.get(User.class,92);
        System.out.println(user);

        session1.getTransaction().commit();*/


    }

    @Test
    public void update() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,91);
        user.setUserName("Hibernate ORM");

        session.getTransaction().commit();
    }

    @Test
    public void delete() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,91);
        session.delete(user);

        session.getTransaction().commit();
    }

    @Test
    public void findAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        String hql = "from User where userName = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,"tom");

        List<User> userList = query.list();

        for(User user : userList) {
            System.out.println(user);
        }
        session.getTransaction().commit();
    }
}
