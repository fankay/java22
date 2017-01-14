package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    void save(User user);

    void del(Integer id);

    User findById(Integer id);

    void update(User user);

    Long count();

    List<User> findByPage(@Param("start") int start,@Param("pageSize") int pageSize);

    Long countByParam(@Param("queryName") String queryName, @Param("queryRole") String queryRole);

    List<User> findByPageAndParam(@Param("start") int start,
                                  @Param("pageSize") int pageSize,
                                  @Param("queryName") String queryName,
                                  @Param("queryRole") String queryRole);

    User findByUserName(String userName);
}
