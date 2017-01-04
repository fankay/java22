package com.kaishengit;

import com.kaishengit.pojo.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MyBatisTestCase {

    @Test
    public void readXml() {

        try {
            //1. 读取classpath中的配置文件
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            //2. 构建SqlSessionFactory对象
            SqlSessionFactory sessionFactory =
                    new SqlSessionFactoryBuilder().build(reader);
            //3. 创建SqlSession对象
            SqlSession sqlSession = sessionFactory.openSession();

            //执行查询单个对象语句
            User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",1);
            System.out.println(user);



            //4. 释放资源
            sqlSession.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",1);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void save() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        User user = new User();
        user.setUserName("mybatis");
        user.setPassword("109876");

        sqlSession.insert("com.kaishengit.mapper.UserMapper.save",user);

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void update() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession(true);

        User user = session.selectOne("com.kaishengit.mapper.UserMapper.findById",58);
        user.setUserName("Spring");

        session.update("com.kaishengit.mapper.UserMapper.update",user);

        session.close();
    }

    @Test
    public void del() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession(true);
        session.delete("com.kaishengit.mapper.UserMapper.del",58);
        session.close();
    }

    @Test
    public void findAll() {
        SqlSession session = SqlSessionFactoryUtil.getSqlSession();

        List<User> userList = session.selectList("com.kaishengit.mapper.UserMapper.findAll");
        for(User user : userList) {
            System.out.println(user);
        }

        session.close();
    }



}
