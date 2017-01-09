package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public void save(User user) throws Exception {
        userDao.save(user);
        userDao.save(user);
    }

    @Override
    //@Transactional(readOnly = true)
    public User findById(Integer id) {
        return userDao.findById(id);
    }
}
