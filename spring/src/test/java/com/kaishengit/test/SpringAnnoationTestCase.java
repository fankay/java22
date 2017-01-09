package com.kaishengit.test;

import com.kaishengit.Application;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ContextConfiguration(classes = Application.class)
public class SpringAnnoationTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        userService.save();
    }

}
