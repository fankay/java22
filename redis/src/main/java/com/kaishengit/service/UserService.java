package com.kaishengit.service;

import com.kaishengit.dao.UserRedisDao;
import com.kaishengit.pojo.User;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRedisDao userRedisDao;

    public void save(User user) {
        userRedisDao.save(user);
    }

    public Iterable<User> findAll() {
        return userRedisDao.findAll();
    }

    public void del(String id) {
        userRedisDao.delete(id);
    }

    public User findByUserName(String userName) {
        return userRedisDao.findByUserName(userName);
    }




   /* @Autowired
    private RedisTemplate redisTemplate;

    public void saveToRedis() {
        User user = new User(102,"张思祺",100F);

        Schema<User> userSchema = RuntimeSchema.getSchema(User.class);
        byte[] userByte = ProtobufIOUtil.toByteArray(user,userSchema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        redisTemplate.opsForValue().set("user:103".getBytes(),userByte);
    }

    public String getFromRedis() {
        Schema<User> userSchema = RuntimeSchema.getSchema(User.class);
        User user = new User();
        byte[] userByte = (byte[]) redisTemplate.opsForValue().get("user:103".getBytes());
        ProtobufIOUtil.mergeFrom(userByte,user,userSchema);

        System.out.println(user);
        return null;
    }*/




    /*@Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
    }

    public void saveToRedis() {
        User user = new User(101,"jack",88.5F);
        redisTemplate.opsForValue().set("user:101",user);
        //redisTemplate.opsForValue().set("book:name:2","Head first Redis");
    }

    public String getFromRedis() {

        User user = (User) redisTemplate.opsForValue().get("user:101");
        System.out.println(user);

        return null;
    }


*/






    //@Autowired
    //private JedisPool jedisPool;

    /*public void saveToRedis() {
        Jedis jedis = jedisPool.getResource();
        jedis.set("spring","jedis");
        jedis.close();
    }*/

}
