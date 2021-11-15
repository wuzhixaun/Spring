package com.wuzx.mvcframework.annotations;


import java.lang.annotation.*;


@Documented
@Target(ElementType.TYPE) // 定义类上面注解
@Retention(RetentionPolicy.RUNTIME) // 定义生命周期
public @interface Controller {
    String value() default "";
}
