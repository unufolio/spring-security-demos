package com.unufolio.spring.security.demos;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/26
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final AuthenticationTokenUserDetailsService authenticationTokenUserDetailsService;

    public WebSecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder,
        @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
        AuthenticationSuccessHandler authenticationSuccessHandler,
        AuthenticationFailureHandler authenticationFailureHandler,
        LogoutSuccessHandler logoutSuccessHandler,
        AuthenticationTokenUserDetailsService authenticationTokenUserDetailsService) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationTokenUserDetailsService = authenticationTokenUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setCreateTableOnStartup(true);
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    public BearerAuthenticationFilter bearerTokenAuthenticationFilter() {
        BearerAuthenticationFilter filter = new BearerAuthenticationFilter();
        filter.setAuthenticationTokenResolver(new DefaultBearerTokenResolver());
        filter.setAuthenticationTokenUserDetailsService(authenticationTokenUserDetailsService);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler);

        http.addFilter(bearerTokenAuthenticationFilter());

        http.logout()
            .logoutSuccessHandler(logoutSuccessHandler);

        http.authorizeRequests().anyRequest().authenticated();
    }
}