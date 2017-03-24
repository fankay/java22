package com.kaishengit;

import com.google.common.collect.Maps;
import com.kaishengit.shiro.ShiroDbRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroDbRealm shiroDbRealm() {
        return new ShiroDbRealm();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setCacheManager(cacheManager());
        webSecurityManager.setRealm(shiroDbRealm());
        return webSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());
        factoryBean.setLoginUrl("/login");
        factoryBean.setSuccessUrl("/home");
        factoryBean.setUnauthorizedUrl("/403");

        Map<String,String> filterMap = Maps.newLinkedHashMap();

        filterMap.put("/**","authc");

        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }

}
