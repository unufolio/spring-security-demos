package com.unufolio.spring.security.demos;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/12/21
 */
public class AuthenticationTokenExpiredException extends AuthenticationException {
    public AuthenticationTokenExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationTokenExpiredException(String msg) {
        super(msg);
    }
}
