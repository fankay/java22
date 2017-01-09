package com.kaishengit.dao.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoImplTest {


    @Autowired
    private UserDao userDao;

    @Test
    public void save() throws Exception {
        User user = new User("Spring 4.x","123123");
        userDao.save(user);
    }

    @Test
    public void update() throws Exception {
        User user = userDao.findById(82);
        user.setPassword("000000");
        userDao.update(user);
    }

    @Test
    public void delete() throws Exception {
        userDao.delete(73);
    }

    @Test
    public void findById() throws Exception {
        User user = userDao.findById(82);
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    public void findAll() throws Exception {
        List<User> userList = userDao.findAll();
        for(User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void findByUserName() throws Exception {
        User user = userDao.findByUserName("tom");
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    public void count() throws Exception {
        Long result = userDao.count();
        System.out.println(result);
    }

}