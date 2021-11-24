package com.lagou.config;

import com.lagou.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName SecurityConfiguration.java
 * @Description Security配置类
 * @createTime 2021年11月24日 16:35:00
 */

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    /**
     * 身份验证管理器
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.httpBasic() // 开启base验证
                .and().authorizeRequests().anyRequest().authenticated(); //所有请求都需要登录认证才能访问*/
        http.formLogin() // 开启表单验证
                .loginPage("/toLoginPage") // 自定义登录页面
                .loginProcessingUrl("/login") // 登录请求url
                .usernameParameter("username")  // 修改自定义表单name值
                .passwordParameter("password")
                .successForwardUrl("/") // 登录成功跳转的路径
                .and().authorizeRequests()
                .antMatchers("/toLoginPage").permitAll() // 放行当前请求
                .anyRequest().authenticated(); //所有请求都需要登录认证才能访问;
        // 关闭csrf防护
        http.csrf().disable();
        // 允许iframe加载页面
        http.headers().frameOptions().sameOrigin();
    }
}
