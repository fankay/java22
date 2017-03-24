package com.kaishengit.service;

import com.kaishengit.entity.User;
import com.kaishengit.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userMapper.findAll();
    }

}
