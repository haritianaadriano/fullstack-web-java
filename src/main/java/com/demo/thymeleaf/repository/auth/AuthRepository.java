package com.demo.thymeleaf.repository.auth;

import com.demo.thymeleaf.model.conf.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<User, UUID> {
    User findUserByUsernameContainingIgnoreCase(String username);
}
