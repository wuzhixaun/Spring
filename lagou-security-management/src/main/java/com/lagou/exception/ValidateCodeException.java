package com.lagou.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author wuzhixuan
 * @version 1.0.0
 * @ClassName ValidateCodeException.java
 * @Description TODO
 * @createTime 2021年11月25日 11:26:00
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
