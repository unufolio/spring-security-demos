package com.unufolio.spring.security.demos;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.AuthenticationException;

@FunctionalInterface
public interface BearerTokenResolver {

    String resolve(HttpServletRequest request) throws AuthenticationException;

}