package com.kaishengit.dao;

import com.kaishengit.pojo.User;

public class UserDao {

    public void save(User user) {
        System.out.println(user.getName() + " saved!");
    }
}
