package com.unufolio.spring.security.demos;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/12/21
 */
public interface AuthenticationTokenUserDetailsService {

    UserDetails loadUserByAuthenticationToken(String token);
}
