package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class NativeSQLQueryTest {

    @Test
    public void findAll() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        String sql = "select * from t_user";
        SQLQuery query = session.createSQLQuery(sql);//.addEntity(User.class);
        List<Object[]> userList = query.list();

        for(Object[] user : userList) {
            System.out.println(user[0] + "->" + user[1]);
        }


        session.getTransaction().commit();
    }

}
