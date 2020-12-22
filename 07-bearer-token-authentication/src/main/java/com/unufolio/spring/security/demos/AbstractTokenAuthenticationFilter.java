package com.unufolio.spring.security.demos;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/27
 */
public abstract class AbstractTokenAuthenticationFilter extends OncePerRequestFilter {

    private BearerTokenResolver bearerTokenResolver;
    private AuthenticationTokenUserDetailsService authenticationTokenUserDetailsService;

    protected AbstractTokenAuthenticationFilter() {
    }

    protected String resolveToken(HttpServletRequest httpServletRequest) {
        return bearerTokenResolver.resolve(httpServletRequest);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        authentication(request, response);
    }

    protected void authentication(HttpServletRequest request, HttpServletResponse response) {
        String token = resolveToken(request);

        UserDetails userDetails = authenticationTokenUserDetailsService.loadUserByAuthenticationToken(token);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
            = getUsernamePasswordAuthenticationToken(userDetails);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    public void setAuthenticationTokenResolver(BearerTokenResolver bearerTokenResolver) {
        this.bearerTokenResolver = bearerTokenResolver;
    }

    public void setAuthenticationTokenUserDetailsService(
        AuthenticationTokenUserDetailsService authenticationTokenUserDetailsService) {
        this.authenticationTokenUserDetailsService = authenticationTokenUserDetailsService;
    }

    public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(UserDetails userDetails) {
        if (userDetails == null) {
            throw new AuthenticationTokenInvalidException("authentication token invalid, please login again");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
