package com.unufolio.spring.security.demos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/12/21
 */
public final class DefaultBearerTokenResolver implements BearerTokenResolver {

    private static final Pattern AUTHORIZATION_PATTERN = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-._~+/]+=*)$",
        Pattern.CASE_INSENSITIVE);

    private boolean allowFormEncodedBodyParameter = false;

    private boolean allowUriQueryParameter = false;

    private String bearerTokenHeaderName = HttpHeaders.AUTHORIZATION;

    private final static String BEARER_PREFIX = "Bearer";

    @Override
    public String resolve(HttpServletRequest request) throws AuthenticationException {
        String authorizationHeaderToken = resolveFromAuthorizationHeader(request);
        String parameterToken = resolveFromRequestParameters(request);
        if (authorizationHeaderToken != null) {
            if (parameterToken != null) {
                throw new TokenAuthenticationException("Found multiple bearer tokens in the request");
            }
            return authorizationHeaderToken;
        }
        if (parameterToken != null && isParameterTokenSupportedForRequest(request)) {
            return parameterToken;
        }
        return null;
    }

    public void setAllowFormEncodedBodyParameter(boolean allowFormEncodedBodyParameter) {
        this.allowFormEncodedBodyParameter = allowFormEncodedBodyParameter;
    }

    public void setAllowUriQueryParameter(boolean allowUriQueryParameter) {
        this.allowUriQueryParameter = allowUriQueryParameter;
    }

    public void setBearerTokenHeaderName(String bearerTokenHeaderName) {
        this.bearerTokenHeaderName = bearerTokenHeaderName;
    }

    private String resolveFromAuthorizationHeader(HttpServletRequest request)
        throws TokenAuthenticationException {
        String authorization = request.getHeader(this.bearerTokenHeaderName);
        if (!StringUtils.startsWithIgnoreCase(authorization, BEARER_PREFIX)) {
            return null;
        }
        Matcher matcher = AUTHORIZATION_PATTERN.matcher(authorization);
        if (!matcher.matches()) {
            throw new TokenAuthenticationException("Bearer token is malformed");
        }
        return matcher.group("token");
    }

    private static String resolveFromRequestParameters(HttpServletRequest request) throws AuthenticationException {
        String[] values = request.getParameterValues("access_token");
        if (values == null || values.length == 0) {
            return null;
        }
        if (values.length == 1) {
            return values[0];
        }
        throw new TokenAuthenticationException("Found multiple bearer tokens in the request");
    }

    private boolean isParameterTokenSupportedForRequest(HttpServletRequest request) {
        return ((this.allowFormEncodedBodyParameter && "POST".equals(request.getMethod()))
            || (this.allowUriQueryParameter && "GET".equals(request.getMethod())));
    }

}
