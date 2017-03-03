package com.kaishengit.dao;

import com.kaishengit.pojo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisDao extends CrudRepository<User,String> {

    User findByUserName(String userName);

}
