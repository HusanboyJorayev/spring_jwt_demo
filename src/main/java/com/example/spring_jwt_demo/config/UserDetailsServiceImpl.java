package com.example.spring_jwt_demo.config;

import com.example.spring_jwt_demo.repository.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser = this.authRepo.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        return new User(appUser.getUsername(), appUser.getPassword(), appUser.getAuthorities());
    }
}
