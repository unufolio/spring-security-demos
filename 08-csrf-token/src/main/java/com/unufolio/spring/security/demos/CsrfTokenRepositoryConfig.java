package com.unufolio.spring.security.demos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/12/22
 */
@Configuration
public class CsrfTokenRepositoryConfig {

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        // header
        csrfTokenRepository.setHeaderName("X-CSRF-TOKEN");
        return csrfTokenRepository;
    }
}
