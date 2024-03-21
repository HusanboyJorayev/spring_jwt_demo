package com.example.spring_jwt_demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/get")
    public ResponseEntity<String> get() {
        return new ResponseEntity<>(UUID.randomUUID().toString(), HttpStatus.OK);
    }
}
