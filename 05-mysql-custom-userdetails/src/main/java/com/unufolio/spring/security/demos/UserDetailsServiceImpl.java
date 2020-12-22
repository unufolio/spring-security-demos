package com.unufolio.spring.security.demos;
import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/27
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO query = new UserDO();
        query.setUsername(username);
        Example<UserDO> example = Example.of(query);
        Optional<UserDO> optionalUserDO = userRepository.findOne(example);
        if (optionalUserDO.isPresent()) {
            UserDO userDO = optionalUserDO.get();
            return CustomUserDetails.withUsername(userDO.getUsername())
                .password(userDO.getPassword())
                .nickname(userDO.getNickname())
                .email(userDO.getEmail())
                .mobile(userDO.getMobile())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .roles("ADMIN").build();
        }
        return null;
    }
}
