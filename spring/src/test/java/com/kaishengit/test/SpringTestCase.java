package com.kaishengit.test;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.impl.UserDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestCase {

    @Test
    public void load() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        /*UserDao userDao =
                applicationContext.getBean("userDaoImpl",UserDaoImpl.class);*/


        /*userDao.save();
        userDao.update();*/

    }

}
