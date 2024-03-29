package com.example.auth.controller;

import com.example.auth.common.GlobalException;
import com.example.auth.model.LoginRequest;
import com.example.auth.model.LoginResponse;
import com.example.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> ping() {
        logger.info("ping");
        return Mono.just(ResponseEntity.ok().body("pong"))
                .onErrorMap(e -> new GlobalException("951", e.getMessage()));
    }

    @PostMapping(value = "/login")
    public Mono<ResponseEntity<LoginResponse>> login(@RequestBody LoginRequest loginRequest) throws GlobalException {
        logger.info("loginRequest = {}", loginRequest);
        return authService.login(loginRequest)
                .map(ResponseEntity::ok)
                .onErrorMap(e -> new GlobalException("951", e.getMessage()))
                .doOnNext(loginResponse -> logger.info("loginResponse = {}", loginResponse));
    }

}
