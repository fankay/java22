package com.kaishengit.service;

import com.kaishengit.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    void save(User user);

    void delUser(Integer id);

    User findUserById(Integer id);

    void editUser(User user);
}
