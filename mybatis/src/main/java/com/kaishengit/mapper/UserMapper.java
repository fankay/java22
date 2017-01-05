package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User findById(Integer id);
    List<User> findAll();
    void update(User user);
    void save(User user);
    void del(Integer id);
    User findByUserNameAndPassword(Map<String,Object> param);
    User findByParam(Map<String,Object> param);
    List<User> findByIds(List<Integer> idList);
    void batchSave(List<User> userList);

}
