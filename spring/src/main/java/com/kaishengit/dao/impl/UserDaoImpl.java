package com.kaishengit.dao.impl;

import com.kaishengit.dao.UserDao;

public class UserDaoImpl implements UserDao {

    public UserDaoImpl() {
        System.out.println("create UserDaoImpl");
    }

    @Override
    public void save() {
        System.out.println("userDaoImpl save");
    }

    @Override
    public void update() {
        System.out.println("userDaoImpl update");
    }
}
