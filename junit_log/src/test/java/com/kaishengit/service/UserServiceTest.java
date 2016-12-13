package com.kaishengit.service;

import com.kaishengit.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService = new UserService();

    @Test
    public void findById() throws Exception {
        User user = userService.findById(7);
        user = userService.findById(7);

        System.out.println(user);
    }

    @Test
    public void findAll() {
        List<User> userList = userService.findAll();
        userList = userService.findAll();

        System.out.println(userList);
    }

    @Test
    public void save() {
        List<User> userList = userService.findAll();
        int beforeSize = userList.size();
        System.out.println("SIZE:" + userList.size());

        User user = new User();
        user.setUsername("Ehcache");
        user.setAge(23);
        user.setAddress("英国");
        user.setTel("0098777");
        userService.save(user);

        userList = userService.findAll();
        int afterSize = userList.size();
        System.out.println("SIZE:" + userList.size());

        Assert.assertEquals(beforeSize+1,afterSize);
    }

}