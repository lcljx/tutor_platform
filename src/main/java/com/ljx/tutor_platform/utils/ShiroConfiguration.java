package com.ljx.tutor_platform.utils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;  
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
   
/** 
 * Shiro 配置 
 * 
Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。 
既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。 
 * 
 * @author Angel(QQ:412887952) 
 * @version v.0.1 
 */  
@Configuration  
public class ShiroConfiguration {  
	@Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }
     
    /** 
     * ShiroFilterFactoryBean 处理拦截资源文件问题。 
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在 
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager 
     * 
        Filter Chain定义说明 
       1、一个URL可以配置多个Filter，使用逗号分隔 
       2、当设置多个过滤器时，全部验证通过，才视为通过 
       3、部分过滤器可指定参数，如perms，roles 
     * 
     */  
    @Bean  
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){  
       System.out.println("ShiroConfiguration.shirFilter()");  
       ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();  
        
        // 必须设置 SecurityManager   
       shiroFilterFactoryBean.setSecurityManager(securityManager);  
        
       LogoutFilter logouts = new LogoutFilter();//设置系统退出后的页面
       logouts.setRedirectUrl("/manage/login.html");
       Map<String, Filter> logout = new HashMap<String, Filter>();
       logout.put("logout", logouts);
       RoleOrFilter roleOrFilter = new RoleOrFilter();
       logout.put("roleOrFilter", roleOrFilter);
       //拦截器.  
       Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();  
        
       //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了  
       filterChainDefinitionMap.put("/logout", "logout");  
       filterChainDefinitionMap.put("/manage/login.html", "anon");
       filterChainDefinitionMap.put("/manage/usermanage/managers.html", "authc,roles[admin]");
       filterChainDefinitionMap.put("/manage/roles/**", "authc,roles[admin]");
       filterChainDefinitionMap.put("/manage/**", "authc,roleOrFilter[admin,nadmin]");
       
       //filterChainDefinitionMap.put("/**", "anon");  
       // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面  
        shiroFilterFactoryBean.setLoginUrl("/manage/login.html");
        // 登录成功后要跳转的链接  
        //shiroFilterFactoryBean.setSuccessUrl("/index.html");
        //未授权界面;  
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");
        shiroFilterFactoryBean.setFilters(logout);
       shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
       return shiroFilterFactoryBean;  
    }  
     
     
    @Bean  
    public SecurityManager securityManager(){  
       DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();  
       //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
       HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
       matcher.setHashAlgorithmName("md5");
       matcher.setHashIterations(3);
       myRealm().setCredentialsMatcher(matcher);
       //设置realm.
       securityManager.setRealm(myRealm());
       return securityManager;

    }  
}
