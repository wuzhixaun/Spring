package com.wuzx.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName EnableRegisterServer.java
 * @Description TODO
 * @createTime 2021年11月22日 10:44:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ConfigMarker.class})

public @interface EnableRegisterServer {
}
