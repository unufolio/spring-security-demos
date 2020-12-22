package com.unufolio.spring.security.demos;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/12/22
 */
public interface BearerAccessToken {

    String BEARER_TYPE = "Bearer";

    String ACCESS_TOKEN = "access_token";

    String TOKEN_TYPE = "token_type";

    String EXPIRES_IN = "expires_in";

    String REFRESH_TOKEN = "refresh_token";

    Map<String, Object> getAdditionalInformation();

    BearerRefreshToken getRefreshToken();

    String getTokenType();

    boolean isExpired();

    LocalDateTime getExpiration();

    int getExpiresIn();

    String getValue();
}