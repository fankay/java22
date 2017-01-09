package com.kaishengit.service;

import com.kaishengit.pojo.User;

public interface UserService {

    void save(User user) throws Exception;
    User findById(Integer id);


}
