package com.cheetah.shiroandjwt.config;


import com.cheetah.shiroandjwt.filter.CustomRolesOrAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Description: shiro配置类
 * @Date: 2020/11/13
 * @Author: cheetah
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class ShiroConfig {

    /**
     *  创建ShiroFilterFactoryBean
     * @author cheetah
     * @date 2020/11/21
     * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //设置shiro内置过滤器
        Map<String, Filter> filters = new LinkedHashMap<>();
        //添加自定义过滤器：只对需要登陆的接口进行过滤
        filters.put("authc", new CustomRolesOrAuthorizationFilter());
        //添加自定义过滤器：对权限进行验证
//        filters.put("roles", new CustomRolesOrAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/adminLogin/login");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/notAuth");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //游客，开发权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        //用户，需要角色权限 “user”
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        filterChainDefinitionMap.put("/productInfo/**", "roles[user]");
        //管理员，需要角色权限 “admin”
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        //开放登陆接口
        filterChainDefinitionMap.put("/adminLogin/login", "anon");
        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        log.info("-------Shiro拦截器工厂类注入成功-----------");
        return shiroFilterFactoryBean;
    }

    /**
     *  注入安全管理器
     * @author cheetah
     * @date 2020/11/21
     * @return: java.lang.SecurityManager
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(customRealm());
        return securityManager;
    }



    /**
     *  注入自定义身份认证
     * @author cheetah
     * @date 2020/11/21
     * @return: com.cheetah.shiroandjwt.config.CustomRealm
     */
    @Bean
    public CustomRealm customRealm(){
        return new CustomRealm();
    }

}
