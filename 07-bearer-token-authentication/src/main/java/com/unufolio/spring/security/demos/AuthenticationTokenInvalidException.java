package com.unufolio.spring.security.demos;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/12/21
 */
public class AuthenticationTokenInvalidException extends AuthenticationException {
    public AuthenticationTokenInvalidException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationTokenInvalidException(String msg) {
        super(msg);
    }
}
