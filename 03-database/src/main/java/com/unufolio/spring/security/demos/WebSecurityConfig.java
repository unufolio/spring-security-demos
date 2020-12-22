package com.unufolio.spring.security.demos;

import javax.sql.DataSource;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/26
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder) {this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder)
            .withDefaultSchema()
            .withUser("unufolio")
            .password(passwordEncoder.encode("unufolio"))
            .roles("ADMIN")
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .and()
            .dataSource(dataSource);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().loginPage("/login").permitAll()
            .failureUrl("/login?error")
            .successForwardUrl("/");

        http.logout().logoutUrl("/logout").
            logoutSuccessUrl("/");

        http.authorizeRequests().anyRequest().authenticated();
    }
}
