package com.example.spring_jwt_demo.controller;

import com.example.spring_jwt_demo.dto.AuthRequestDto;
import com.example.spring_jwt_demo.dto.AuthResponseDto;
import com.example.spring_jwt_demo.dto.AuthStatus;
import com.example.spring_jwt_demo.service.AuthService;
import com.example.spring_jwt_demo.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto dto) {
        var jwtToken = this.authServiceImpl.login(dto.getUsername(), dto.getPassword());
        var responseDto = new AuthResponseDto(jwtToken, AuthStatus.SUCCESS);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody AuthRequestDto dto) {
        var jwtToken = this.authServiceImpl.signUp(dto.getName(), dto.getUsername(), dto.getPassword());
        var responseDto = new AuthResponseDto(jwtToken, AuthStatus.SUCCESS);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
