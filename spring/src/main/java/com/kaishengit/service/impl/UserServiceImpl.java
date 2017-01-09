package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void save() {
        userDao.save();
    }

    @Override
    public void update() {
        userDao.update();
    }

    public int getNum() {
        System.out.println("getNum method...");
        if(1==1) {
            throw new RuntimeException("故意引发的异常");
        }
        return 100;
    }
}
