package com.unufolio.spring.security.demos;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/30
 */
public class TokenAuthenticationException extends AuthenticationException {

    public TokenAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public TokenAuthenticationException(String msg) {
        super(msg);
    }
}
