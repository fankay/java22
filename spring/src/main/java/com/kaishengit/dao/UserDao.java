package com.kaishengit.dao;

import com.kaishengit.pojo.User;

import java.util.List;

public interface UserDao {

    void save(User user);
    void update(User user);
    void delete(Integer id);
    User findById(Integer id);
    List<User> findAll();
    User findByUserName(String username);
    Long count();


}
