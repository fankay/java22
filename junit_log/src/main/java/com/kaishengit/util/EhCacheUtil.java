package com.kaishengit.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import java.io.Serializable;

/**
 * Ehcache工具类
 */
public class EhCacheUtil {

    private static CacheManager cacheManager = new CacheManager();

    public Ehcache getEhCache(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

    public void set(Ehcache ehcache,Serializable key,Serializable value) {
        Element element = new Element(key,value);
        ehcache.put(element);
    }

    public void set(String cacheName,Serializable key,Serializable value) {
        Element element = new Element(key,value);
        getEhCache(cacheName).put(element);
    }

    public void set(String cacheName,Object key,Object value) {
        Element element = new Element(key,value);
        getEhCache(cacheName).put(element);
    }

    public Object get(String cacheName,Serializable key) {
        Element element = getEhCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }
    public Object get(Ehcache ehcache,Serializable key) {
        Element element = ehcache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public void removeAll(String cacheName) {
        getEhCache(cacheName).removeAll();
    }

    public void removeAll(Ehcache ehcache) {
        ehcache.removeAll();
    }

    public void remove(String cacheName,Serializable key) {
       getEhCache(cacheName).remove(key);
    }

    public void remove(Ehcache ehcache,Serializable key) {
        ehcache.remove(key);
    }


}
