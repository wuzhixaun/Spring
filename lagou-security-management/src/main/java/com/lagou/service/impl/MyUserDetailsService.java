package com.lagou.service.impl;

import com.lagou.domain.User;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName MyUserDetailsService.java
 * @Description TODO
 * @createTime 2021年11月24日 17:14:00
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);// 用户名没有找到
        }
        // 先声明一个权限集合, 因为构造方法里面不能传入null
        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(username,
                "{bcrypt}" + user.getPassword(),// {noop}表示不加密认证
                true, // 用户是否启用 true 代表启用
                true,// 用户是否过期 true 代表未过期
                true,// 用户凭据是否过期 true 代表未过期
                true,// 用户是否锁定 true 代表未锁定
                authorities
                );
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 10; i++) {
            final String encode = bCryptPasswordEncoder.encode("123456");
            System.out.println(encode);

            System.out.println(bCryptPasswordEncoder.upgradeEncoding(encode));
        }



    }
}
