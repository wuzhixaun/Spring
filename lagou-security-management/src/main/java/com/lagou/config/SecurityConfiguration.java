package com.lagou.config;

import com.lagou.filter.ValidateCodeFilter;
import com.lagou.service.impl.MyAuthenticationService;
import com.lagou.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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

    @Autowired
    private MyAuthenticationService myAuthenticationService;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;
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

        // 加在用户名密码过滤器的前面
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin() // 开启表单验证
                .loginPage("/toLoginPage") // 自定义登录页面
                .loginProcessingUrl("/login") // 登录请求url
                .usernameParameter("username")  // 修改自定义表单name值
                .passwordParameter("password")
                .successForwardUrl("/") // 登录成功跳转的路径
                .successHandler(myAuthenticationService) //自定义登录成功处理
                .failureHandler(myAuthenticationService) //自定义登录失败处理
                .and().logout().logoutUrl("/logout")
                .logoutSuccessHandler(myAuthenticationService)
                .and().authorizeRequests()
                .antMatchers("/toLoginPage","/code/**").permitAll() // 放行当前请求
                .anyRequest().authenticated() //所有请求都需要登录认证才能访问;
                .and().rememberMe() // 开启记住我功能
                .tokenValiditySeconds(20) // token失效时间
                .rememberMeParameter("remember-me")
                .tokenRepository(getPersistentTokenRepository());

        // session管理
        http.sessionManagement() //设置session管理
                .invalidSessionUrl("/toLoginPage") // 失效跳转页面，默认是登录页
                .maximumSessions(1) //设置session最大会话数量 ,1同一时间只能有一个
                .maxSessionsPreventsLogin(false)//当达到最大会话个数时阻止登录
                .expiredUrl("/toLoginPage");//设置session过期后跳转路径

        // 关闭csrf防护
        http.csrf().disable();
        // 允许iframe加载页面
        http.headers().frameOptions().sameOrigin();
    }


    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository getPersistentTokenRepository() {
        final JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 启动时创建一张表, 第一次启动的时候创建, 第二次启动的时候需要注释掉, 否则 会报错
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }
}
