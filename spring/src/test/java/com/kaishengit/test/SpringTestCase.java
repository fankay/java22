package com.kaishengit.test;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.impl.UserDaoImpl;
import com.kaishengit.service.UserService;
import com.kaishengit.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestCase {

    @Test
    public void load() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = applicationContext.getBean("userService", UserServiceImpl.class);
        userService.save();

    }

}
