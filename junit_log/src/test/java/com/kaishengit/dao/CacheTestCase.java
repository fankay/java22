package com.kaishengit.dao;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.junit.Test;

public class CacheTestCase {

    @Test
    public void testCache() {
        CacheManager cacheManager = new CacheManager();
        Ehcache ehcache = cacheManager.getEhcache("user");
        //添加
        Element element = new Element("user:1","jack");
        ehcache.put(element);

        //删除
        //ehcache.removeAll();
        //ehcache.remove("user:1");

        //取值
        Element e = ehcache.get("user:1");
        System.out.println(e.getObjectValue());
    }

}
