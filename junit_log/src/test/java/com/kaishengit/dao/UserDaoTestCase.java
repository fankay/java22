package com.kaishengit.dao;

import com.kaishengit.entity.User;
import static org.junit.Assert.*; //静态导入
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserDaoTestCase {

    private UserDao userDao;

    @Before
    public void before() {
        //....
        userDao = new UserDao();
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("junit");
        user.setAge(4);
        user.setTel("028-2394825");
        user.setAddress("美国");

        userDao.save(user);
    }

    @Test
    public void testFindById() {
        User user = userDao.findById(7);
        user = userDao.findById(7);
        user = userDao.findById(6);

        assertNotNull(user);
    }

    @Test
    public void testFindAll() {
        List<User> userList = userDao.findAll();
        assertEquals(4,userList.size());
    }

    @Test
    public void testDel() {
        userDao.del(1);
    }

    @Test
    public void testSystem() {
        String str = System.getProperty("java.io.tmpdir");
        System.out.println(str);

        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
    }
}
