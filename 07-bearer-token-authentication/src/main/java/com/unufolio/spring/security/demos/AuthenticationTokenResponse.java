package com.unufolio.spring.security.demos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/30
 */
public class AuthenticationTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    private String jti;

    public AuthenticationTokenResponse() {
    }

    public AuthenticationTokenResponse(String accessToken, String refreshToken, String tokenType, int expiresIn,
        String jti) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.jti = jti;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    @Override
    public String toString() {
        return "BearerTokenResponse{" +
            "accessToken='" + accessToken + '\'' +
            ", refreshToken='" + refreshToken + '\'' +
            ", tokenType='" + tokenType + '\'' +
            ", expiresIn=" + expiresIn +
            ", jti='" + jti + '\'' +
            '}';
    }
}
