package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

import java.util.List;

public interface UserMapper {

    User findById(Integer id);
    List<User> findAll();
    void update(User user);
    void save(User user);
    void del(Integer id);

}
