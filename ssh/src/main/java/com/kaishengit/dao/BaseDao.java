package com.kaishengit.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class clazz;

    public BaseDao () {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class) type.getActualTypeArguments()[0];
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public T findById(Integer id) {
        return (T) getSession().get(clazz,id);
    }
    public void delete(Integer id) {
        getSession().delete(findById(id));
    }
    public List<T> findAll() {
        Criteria criteria = getSession().createCriteria(clazz);
        return criteria.list();
    }
    public List<T> findAll(String propertyName,String orderType) {
        Criteria criteria = getSession().createCriteria(clazz);
        if("desc".equalsIgnoreCase(orderType)) {
            criteria.addOrder(Order.desc(propertyName));
        } else {
            criteria.addOrder(Order.asc(propertyName));
        }
        return criteria.list();
    }

}
