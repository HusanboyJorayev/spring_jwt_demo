package com.example.spring_jwt_demo.service;


import org.springframework.stereotype.Component;

@Component
public interface AuthService {
    String login(String username, String password);

    String signUp(String name, String username, String password);
}
