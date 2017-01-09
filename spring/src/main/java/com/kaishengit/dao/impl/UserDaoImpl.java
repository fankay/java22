package com.kaishengit.dao.impl;

import com.kaishengit.dao.UserDao;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.inject.Named;

//@Repository
//@Service
//@Named
//@Scope("prototype")
//@Lazy
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
