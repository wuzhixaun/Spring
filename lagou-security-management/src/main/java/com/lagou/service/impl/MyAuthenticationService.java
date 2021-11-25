package com.lagou.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName MyAuthenticationService.java
 * @Description 自定义登录成功或失败处理类
 * @createTime 2021年11月25日 10:12:00
 */
@Service
public class MyAuthenticationService implements AuthenticationSuccessHandler, AuthenticationFailureHandler, LogoutSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("登录失败后续处理....");
        //重定向到login页
//        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/toLoginPage");
        Map result = new HashMap();
        result.put("code", HttpStatus.UNAUTHORIZED.value());// 设置响应码
        result.put("message", e.getMessage());// 设置错误信息
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功后续处理....");
        //重定向到index页
//         redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
        Map result = new HashMap();
        result.put("code", HttpStatus.OK.value());// 设置响应码
        result.put("message", "登录成功");// 设置响应信息
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

    }


    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("退出成功后续处理....");
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/toLoginPage");
    }
}
