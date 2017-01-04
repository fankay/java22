package com.kaishengit;

import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MyBaitsInterfaceTestCase {

    @Test
    public void findById() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();

        //自动产生一个UserMapper接口的实现类（代理对象）
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findById(1);
        System.out.println(user);

        session.close();
    }

    @Test
    public void save() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession(true);

        User user = new User();
        user.setUserName("Rose");
        user.setPassword("123123");

        UserMapper userMapper = session.getMapper(UserMapper.class);
        userMapper.save(user);

        System.out.println(user.getId());

        session.commit();
        session.close();
    }

}
