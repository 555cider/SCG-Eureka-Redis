package com.example.auth.utils;

import com.example.auth.model.Auth;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.expiration}")
    private static int jwtExpiration;

    public static String createJwt(Auth auth) {
        logger.info("jwt exp = {}", jwtExpiration);
        return Jwts.builder()
                .issuer("me")
                .subject(auth.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()))
                .compact();
    }

}
