package com.example.spring_jwt_demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static io.jsonwebtoken.Jwts.*;

@Component
@Slf4j
public class JwtUtils {
    private static final String ISSUER = "Zero_to_Hero";

    private JwtUtils() {
    }

    public static boolean validateToken(String jwtToken) {
        return parseToken(jwtToken).isPresent();
    }

    private static Optional<Claims> parseToken(String jwtToken) {
        return Optional.of(parserBuilder()
                .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody());

    }

    public static Optional<String> getUsernameFromToken(String jwtToken) {
        var claimsOptional = parseToken(jwtToken);
        return claimsOptional.map(claims -> Optional.of(claims.getSubject())).orElse(null);
    }

    public static String generateToken(String username) {
        return Jwts
                .builder()
                .setId(UUID.randomUUID().toString())
                .setIssuer(ISSUER)
                .setSubject(username)
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .setIssuedAt(new Date())
                .setExpiration(DateUtils.addMinutes(new Date(), 30))
                .compact();
    }
}
