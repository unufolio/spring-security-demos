package com.unufolio.spring.security.demos;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/12/21
 */
@Service
public class DefaultAuthenticationTokenUserDetailsServiceImpl implements AuthenticationTokenUserDetailsService {

    @Override
    public UserDetails loadUserByAuthenticationToken(String token) {
        return CustomUserDetails.withUsername("username").build();
    }

}
