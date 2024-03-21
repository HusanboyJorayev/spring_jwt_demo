package com.example.spring_jwt_demo.repository;

import com.example.spring_jwt_demo.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepo extends JpaRepository<AppUser,Integer> {
    boolean existsByUsername(String username);
    Optional<AppUser>findByUsername(String username);
}
