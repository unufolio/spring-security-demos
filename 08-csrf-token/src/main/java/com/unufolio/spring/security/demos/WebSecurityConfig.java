package com.unufolio.spring.security.demos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfTokenRepository;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/26
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CsrfTokenRepository csrfTokenRepository;

    public WebSecurityConfig(CsrfTokenRepository csrfTokenRepository) {this.csrfTokenRepository = csrfTokenRepository;}

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().csrfTokenRepository(csrfTokenRepository);

        // disable csrf
        // http.csrf().disable();

        http.authorizeRequests().anyRequest().authenticated();

        http.formLogin().loginPage("/login").permitAll()
            .failureHandler((request, response, exception) -> {
                exception.printStackTrace();
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            })
            .successHandler((request, response, authentication) -> {
                System.out.println("success");
                response.setStatus(HttpStatus.OK.value());
            });

        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            accessDeniedException.printStackTrace();
            response.setStatus(HttpStatus.FORBIDDEN.value());
        });
    }
}
