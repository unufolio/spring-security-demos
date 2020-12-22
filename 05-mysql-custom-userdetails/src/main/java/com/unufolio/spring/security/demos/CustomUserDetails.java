package com.unufolio.spring.security.demos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/27
 */
public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String nickname;
    private final String password;
    private final String email;
    private final String mobile;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public CustomUserDetails(String username, String password, String nickname, String email, String mobile,
        boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired,
        List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.mobile = mobile;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.authorities = authorities;
    }

    public static UserBuilder withUsername(String username) {
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.username(username);
        return userBuilder;
    }

    public static final class UserBuilder {

        private String username;

        private String password;

        private String nickname;

        private String email;

        private String mobile;

        private List<GrantedAuthority> authorities;

        private boolean accountExpired;

        private boolean accountLocked;

        private boolean credentialsExpired;

        private boolean disabled;

        private UserBuilder() {
        }

        public CustomUserDetails.UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public CustomUserDetails.UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public CustomUserDetails.UserBuilder nickname(String nickname) {
            Assert.notNull(nickname, "nickname cannot be null");
            this.nickname = nickname;
            return this;
        }

        public CustomUserDetails.UserBuilder email(String email) {
            Assert.notNull(email, "email cannot be null");
            this.email = email;
            return this;
        }

        public CustomUserDetails.UserBuilder mobile(String mobile) {
            Assert.notNull(mobile, "email cannot be null");
            this.mobile = mobile;
            return this;
        }

        public CustomUserDetails.UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
            for (String role : roles) {
                Assert.isTrue(!role.startsWith("ROLE_"),
                    () -> role + " cannot start with ROLE_ (it is automatically added)");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
            return authorities(authorities);
        }

        public CustomUserDetails.UserBuilder authorities(GrantedAuthority... authorities) {
            return authorities(Arrays.asList(authorities));
        }

        public CustomUserDetails.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        public CustomUserDetails.UserBuilder authorities(String... authorities) {
            return authorities(AuthorityUtils.createAuthorityList(authorities));
        }

        public CustomUserDetails.UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public CustomUserDetails.UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public CustomUserDetails.UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public CustomUserDetails.UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetails build() {
            return new CustomUserDetails(this.username, this.password, this.nickname, this.email, this.mobile,
                !this.disabled, !this.accountExpired, !this.accountLocked, !this.credentialsExpired, this.authorities);
        }

    }
}
