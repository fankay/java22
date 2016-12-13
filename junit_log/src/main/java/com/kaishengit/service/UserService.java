package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.EhCacheUtil;

import java.util.List;

public class UserService {

    private static final String USER_CACHE_NAME = "user";

    private UserDao userDao = new UserDao();
    private EhCacheUtil cacheUtil = new EhCacheUtil();

    public User findById(Integer id) {
        User user = (User) cacheUtil.get(USER_CACHE_NAME,"user:"+id);
        if(user == null) {
            user = userDao.findById(id);
            cacheUtil.set(USER_CACHE_NAME,"user:"+id,user);
        }
        return user;
    }

    public List<User> findAll() {
        List<User> userList = (List<User>) cacheUtil.get(USER_CACHE_NAME,"userList");
        if(userList == null) {
            userList = userDao.findAll();
            cacheUtil.set(USER_CACHE_NAME,"userList",userList);
        }
        return userList;
    }

    public void save(User user) {
        userDao.save(user);
        cacheUtil.remove(USER_CACHE_NAME,"userList");
    }

    public void del(Integer id) {
        userDao.del(id);
        cacheUtil.removeAll(USER_CACHE_NAME);
//        cacheUtil.remove(USER_CACHE_NAME,"userList");
//        cacheUtil.remove(USER_CACHE_NAME,"user:"+id);
    }

}
