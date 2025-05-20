package com.goormthonuniv.hoseo.hosiktamtam.repository.user;

import com.goormthonuniv.hoseo.hosiktamtam.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthId(String oauthId);
}