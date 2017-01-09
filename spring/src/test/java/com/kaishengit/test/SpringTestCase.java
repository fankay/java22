package com.kaishengit.test;

import com.kaishengit.Application;
import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.impl.UserDaoImpl;
import com.kaishengit.service.UserService;
import com.kaishengit.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestCase {

    @Test
    public void load() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(Application.class);
               // new ClassPathXmlApplicationContext("applicationContext.xml");


        /*UserDao userDao = (UserDao) applicationContext.getBean("userDaoImpl");
        userDao.save();*/


        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.save();
        userService.getNum();

    }

}
