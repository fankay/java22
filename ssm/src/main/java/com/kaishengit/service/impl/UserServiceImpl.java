package com.kaishengit.service.impl;

import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Value("${password.salt}")
    private String salt;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAll();
    }

    @Override
    public void save(User user) {
        logger.debug("SALT:" + salt);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));
        userMapper.save(user);
    }

    @Override
    @Transactional
    public void delUser(Integer id) {
        //删除用户的角色
        roleMapper.delRoleByUserId(id);
        //删除用户
        userMapper.del(id);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    @Transactional
    public void editUser(User user, Integer[] roleIds) {
        //删除原有角色
        roleMapper.delRoleByUserId(user.getId());
        //添加新角色
        if(roleIds != null) {
            for(Integer roleId : roleIds) {
                Role role = roleMapper.findById(roleId);
                if(role != null) {
                    roleMapper.saveNewUserRole(user.getId(),roleId);
                }
            }
        }
        //更新用户
        if(StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));
        }
        userMapper.update(user);
    }

    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAll();
    }

    @Override
    @Transactional
    public void saveNewUser(User user, Integer[] roleIds) {
        //1.保存用户
        userMapper.save(user);
        //2.保存用户和角色的关系
        if(roleIds != null) {
            for(Integer roleId : roleIds) {
                Role role = roleMapper.findById(roleId);
                if(role != null) {
                    //创建关系表记录
                    roleMapper.saveNewUserRole(user.getId(),roleId);
                }
            }
        }
    }
}
