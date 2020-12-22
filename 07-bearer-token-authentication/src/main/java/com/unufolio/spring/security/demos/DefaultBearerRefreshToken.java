package com.unufolio.spring.security.demos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/12/22
 */
public class DefaultBearerRefreshToken implements Serializable, BearerRefreshToken {

    private LocalDateTime expiration;

    private String value;

    public DefaultBearerRefreshToken() {
    }

    public DefaultBearerRefreshToken(String value, LocalDateTime expiration) {
        this.value = value;
        this.expiration = expiration;
    }

    @Override
    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultBearerRefreshToken)) {
            return false;
        }

        DefaultBearerRefreshToken that = (DefaultBearerRefreshToken)o;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
