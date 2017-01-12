package com.kaishengit.service;

import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    void save(User user);

    void delUser(Integer id);

    User findUserById(Integer id);

    void editUser(User user);

    List<Role> findAllRole();

    void saveNewUser(User user, Integer[] roleIds);
}
