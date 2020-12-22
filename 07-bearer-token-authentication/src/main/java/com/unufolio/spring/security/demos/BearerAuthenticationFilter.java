package com.unufolio.spring.security.demos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/27
 */
public class BearerAuthenticationFilter extends AbstractTokenAuthenticationFilter {

    private final static Logger logger = LoggerFactory.getLogger(BearerAuthenticationFilter.class);

    public BearerAuthenticationFilter() {
        DefaultBearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();
        bearerTokenResolver.setAllowFormEncodedBodyParameter(true);
        bearerTokenResolver.setAllowUriQueryParameter(true);
        super.setAuthenticationTokenResolver(bearerTokenResolver);
    }

}
