package com.unufolio.spring.security.demos;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/12/22
 */
public class DefaultBearerAccessToken implements Serializable, BearerAccessToken {

    private String value;

    private LocalDateTime expiration;

    private String tokenType = BEARER_TYPE;

    private BearerRefreshToken refreshToken;

    private Map<String, Object> additionalInformation = Collections.emptyMap();

    public DefaultBearerAccessToken(String value) {
        this.value = value;
    }

    private DefaultBearerAccessToken() {
        this((String)null);
    }

    public DefaultBearerAccessToken(BearerAccessToken accessToken) {
        this(accessToken.getValue());
        setAdditionalInformation(accessToken.getAdditionalInformation());
        setRefreshToken(accessToken.getRefreshToken());
        setExpiration(accessToken.getExpiration());
        setTokenType(accessToken.getTokenType());
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public LocalDateTime getExpiration() {
        return expiration;
    }

    @Override
    public int getExpiresIn() {
        if (expiration != null) {
            Duration duration = Duration.between(expiration, LocalDateTime.now());
            return (int)duration.toSeconds();
        }
        return 0;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    @Override
    public String getTokenType() {
        return tokenType;
    }

    @Override
    public boolean isExpired() {
        return expiration != null && expiration.isBefore(LocalDateTime.now());
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public BearerRefreshToken getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(BearerRefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
