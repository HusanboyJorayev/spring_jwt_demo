package com.example.spring_jwt_demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {
    private String name;
    private String username;
    private String password;
}
