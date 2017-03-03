package com.kaishengit;

import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class SpringTest {
    @Autowired
    private UserService userService;


    @Test
    public void save() {
        User user = new User(102,"lisi",30F);
        userService.save(user);
    }

    @Test
    public void findAll() {
        Iterator<User> userIterator = userService.findAll().iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            System.out.println(user);
        }
    }

    @Test
    public void del() {
        userService.del("101");
    }

    @Test
    public void findByUserName() {
        System.out.println(userService.findByUserName("lisi"));
    }

   /* @Test
    public void testSaveToRedis() {
        userService.saveToRedis();
    }

    @Test
    public void testGet() {
       userService.getFromRedis();
    }
*/
}
