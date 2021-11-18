package com.wuzx.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //表面这个是一个配置类
public class MyConfig {


    @Bean // 将这个 id是方法名存到容器中
    public MyService myService() {
        return new MyService();
    }
}
