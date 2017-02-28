package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;

public class UserService {

    private UserDao userDao = new UserDao();

    public void save(User user) {
        userDao.save(user);
    }
}
