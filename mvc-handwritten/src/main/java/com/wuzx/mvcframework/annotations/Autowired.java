package com.wuzx.mvcframework.annotations;


import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD) // 定义属性上面注解
@Retention(RetentionPolicy.RUNTIME) // 定义生命周期
public @interface Autowired {
    String value() default "";
}
