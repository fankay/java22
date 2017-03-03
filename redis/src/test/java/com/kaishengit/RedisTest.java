package com.kaishengit;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisTest {

    @Test
    public void stringSet() {
        Jedis jedis = new Jedis("127.0.0.1",6379,50000);
        jedis.set("name","kaishengit");
        jedis.close();
    }

    @Test
    public void stringGet() {
        Jedis jedis = new Jedis();
        String name = jedis.get("name");
        jedis.close();
        Assert.assertEquals("kaishengit",name);
    }

    @Test
    public void testPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMinIdle(10);
        config.setMaxWaitMillis(5000);

        JedisPool pool = new JedisPool(config,"127.0.0.1",6379);//创建连接池

        Jedis jedis = pool.getResource();//获取连接资源
        jedis.lpush("names","jack","rose","李斯");
        jedis.close();
        pool.destroy();
    }

    @Test
    public void testList() {
        Jedis jedis = new Jedis();

        List<String> names = jedis.lrange("names",0,-1);
        for(String name : names) {
            System.out.println(name);
        }

        /*String name = jedis.rpop("names");
        System.out.println(name);*/
        jedis.close();
    }

    @Test
    public void testHash() {
        Jedis jedis = new Jedis();
        /*Map<String,String> user = new HashMap<>();
        user.put("id","101");
        user.put("name","张思祺");
        jedis.hmset("user:1",user);*/
        String id = jedis.hget("user:1","id");
        System.out.println(id);



        jedis.close();
    }
}
