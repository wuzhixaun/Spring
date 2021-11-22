package com.wuzx.config;

import com.wuzx.pojo.SimpleBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Handler;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName MyConfiguration.java
 * @Description TODO
 * @createTime 2021年11月22日 10:24:00
 */
@Configuration
@ConditionalOnBean(ConfigMarker.class) //就是说只有在classpath下能找到KafkaTemplate类才会构建这个bean
public class MyAutoConfiguration {

    static {
        System.out.println("MyAutoConfiguration init....");
    }

    @Bean
    public SimpleBean simpleBean(){
        return new SimpleBean();
    }

}
