package com.goormthonuniv.hoseo.hosiktamtam.security;

import com.goormthonuniv.hoseo.hosiktamtam.domain.user.User;
import com.goormthonuniv.hoseo.hosiktamtam.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getId().toString(),
                "", // No password as we use OAuth
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
