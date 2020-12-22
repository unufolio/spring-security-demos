package com.unufolio.spring.security.demos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2020/11/27
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {
}
