package com.unufolio.spring.security.demos;

import java.time.LocalDateTime;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/12/22
 */
public interface BearerRefreshToken {

    String getValue();

    LocalDateTime getExpiration();
}
