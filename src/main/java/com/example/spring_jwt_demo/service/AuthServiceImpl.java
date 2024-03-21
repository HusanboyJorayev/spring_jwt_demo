package com.example.spring_jwt_demo.service;

import com.example.spring_jwt_demo.model.AppUser;
import com.example.spring_jwt_demo.repository.AuthRepo;
import com.example.spring_jwt_demo.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthRepo authRepo;

    @Override
    public String login(String username, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        var authenticate = authenticationManager.authenticate(authenticationToken);
        var principal = (UserDetails) (authenticate.getPrincipal());
        return JwtUtils.generateToken(principal.getUsername());
    }

    @Override
    public String signUp(String name, String username, String password) {
        if (authRepo.existsByUsername(username)) {
            throw new RuntimeException("User is already exist");
        }
        var authority = new ArrayList<GrantedAuthority>();
        authority.add(new SimpleGrantedAuthority("ROLE_USER"));
        AppUser user = AppUser.builder()
                .name(name)
                .username(username)
                .password(passwordEncoder.encode(password))
                .authorities(authority)
                .build();
        this.authRepo.save(user);
        return JwtUtils.generateToken(username);
    }
}
