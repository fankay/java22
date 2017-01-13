package com.kaishengit.service;

import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.util.db.Page;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    void save(User user);

    void delUser(Integer id);

    User findUserById(Integer id);

    void editUser(User user, Integer[] roleIds);

    List<Role> findAllRole();

    void saveNewUser(User user, Integer[] roleIds);

    Page<User> findUserByPageNo(Integer pageNo);

    Page<User> findUserByPageNoAndSearchParam(Integer p, String queryName, String queryRole);
}
