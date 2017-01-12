package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    void save(User user);

    void del(Integer id);

    User findById(Integer id);

    void update(User user);
}
