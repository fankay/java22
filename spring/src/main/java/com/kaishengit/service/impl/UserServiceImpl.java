package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

   /* public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    public UserServiceImpl(UserDao userDao) {
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
}
