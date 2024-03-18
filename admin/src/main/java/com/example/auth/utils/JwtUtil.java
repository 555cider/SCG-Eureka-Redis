package com.example.auth.utils;

import java.security.KeyPair;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.example.auth.model.Auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;

public class JwtUtil {

    @Value("${jwt.key.public}")
    private String jwtPublicKey;

    @Value("${jwt.key.secret}")
    private String jwtSecretKey;

    @Value("${jwt.experation}")
    private static int jwtExpiration;

    static SignatureAlgorithm alg = Jwts.SIG.ES256;

    static KeyPair pair = alg.keyPair().build();

    public static String createJwt(Auth auth) {
        return Jwts.builder()
                .header().keyId("login").and()
                .issuer("me")
                .subject(auth.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .encryptWith(pair.getPublic(), alg)
                // signWith(pair.getPrivate(), alg) // <-- Bob's EC private key
                .compact();
    }
    // Alice receives and verifies the compact JWS came from Bob:
    // String subject = Jwts.parser()
    // .verifyWith(pair.getPublic()) // <-- Bob's EC public key
    // .build().parseSignedClaims(jws).getPayload().getSubject();

}
