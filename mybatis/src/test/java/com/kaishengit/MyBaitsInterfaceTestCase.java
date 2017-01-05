package com.kaishengit;

import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class MyBaitsInterfaceTestCase {

    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void setup() {
        sqlSession = SqlSessionFactoryUtil.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void close() {
        sqlSession.close();
    }

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

    @Test
    public void findByUserNameAndPassword() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("name","tom");
        param.put("pwd","123123");

        User user = userMapper.findByUserNameAndPassword(param);
        System.out.println(user);

        session.close();
    }

    @Test
    public void findByParam() {
        Map<String,Object> param = new HashMap<String,Object>();
        //param.put("username","tom");
        param.put("password","111");

        User user = userMapper.findByParam(param);
        System.out.println(user);
    }

    @Test
    public void findByIds() {
        List<User> userList = userMapper.findByIds(Arrays.asList(1,70,71));
        for(User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void batchSave() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("王五","99887"));
        userList.add(new User("咋喽","3344876"));
        userList.add(new User("呵呵","998876"));

        userMapper.batchSave(userList);
        sqlSession.commit();
    }

    @Test
    public void findByCache() {
        //一级缓存：同一个SqlSession中（默认开启）
        User user = userMapper.findById(1);
        user = userMapper.findById(1);
        user = userMapper.findById(1);

        System.out.println(user);
    }

    @Test
    public void findByCache2() {
        //二级缓存（默认关闭）同一个SqlSessionFactory中

        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(1);
        System.out.println(user);
        sqlSession.close();

        SqlSession sqlSession2 = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        user = userMapper2.findById(1);
        System.out.println(user);
        sqlSession2.close();


    }

}
