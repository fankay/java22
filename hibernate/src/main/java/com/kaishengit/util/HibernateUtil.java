package com.kaishengit.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory = builderSessionFactory();

    private static SessionFactory builderSessionFactory() {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().getCurrentSession();
    }
}
