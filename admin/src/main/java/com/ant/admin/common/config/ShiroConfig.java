package com.ant.admin.common.config;

import com.ant.admin.common.cache.RedisCache;
import com.ant.admin.common.cache.RedisCacheManager;
import com.ant.admin.common.shiro.AuthRealm;
import com.ant.admin.common.shiro.RedisSessionDao;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author zhaopinchao
 * @date 2018-7-28 08:19
 */

@Configuration
public class ShiroConfig {

    private static Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login.html");
        bean.setSuccessUrl("/");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();

        /*filterChainDefinitionMap.put("/*", "anon");//表示可以匿名访问
        filterChainDefinitionMap.put("/**", "anon");
        filterChainDefinitionMap.put("/*.*", "anon");*/

        filterChainDefinitionMap.put("/statics/**", "anon");//表示可以匿名访问
        filterChainDefinitionMap.put("/upload/**", "anon");//表示可以匿名访问
        filterChainDefinitionMap.put("/login.html", "anon");
        filterChainDefinitionMap.put("/editor.html", "anon");
        filterChainDefinitionMap.put("/sys/login", "anon");
        filterChainDefinitionMap.put("/captcha.jpg", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");
        filterChainDefinitionMap.put("/", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/*.*", "authc");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }


    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm,
                                           @Qualifier("customSessionManager") CustomSessionManager customSessionManager,
                                           @Qualifier("redisCacheManager") RedisCacheManager redisCacheManager,
                                           @Qualifier("cookieRememberMeManager") CookieRememberMeManager cookieRememberMeManager) {
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        manager.setSessionManager(customSessionManager);
        manager.setCacheManager(redisCacheManager);
        manager.setRememberMeManager(cookieRememberMeManager);


        return manager;
    }
    //配置自定义的权限登录器
    @Bean(name="authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm=new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }
    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    //配置自定义的sessionManager
    @Bean(name="customSessionManager")
    public CustomSessionManager customSessionManager(@Qualifier("redisSessionDao") RedisSessionDao redisSessionDao){
        CustomSessionManager sessionManager =  new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDao);
        return sessionManager;
    }

    @Bean(name="redisSessionDao")
    public RedisSessionDao redisSessionDao(){
        return new RedisSessionDao();
    }

    @Bean(name="redisCacheManager")
    public RedisCacheManager redisCacheManager(@Qualifier("redisCache") RedisCache redisCache){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisCache(redisCache);
        return redisCacheManager;
    }

    @Bean(name="redisCache")
    public RedisCache redisCache(){
        return new RedisCache();
    }

    @Bean(name="cookieRememberMeManager")
    public CookieRememberMeManager cookieRememberMeManager(@Qualifier("simpleCookie") SimpleCookie simpleCookie){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie);
        return cookieRememberMeManager;
    }

    @Bean(name = "simpleCookie")
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("remberMe");
        simpleCookie.setMaxAge(604800);
        return simpleCookie;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

}
