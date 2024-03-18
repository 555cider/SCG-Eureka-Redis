package com.example.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.common.GlobalException;
import com.example.auth.model.LoginRequest;
import com.example.auth.model.LoginResponse;
import com.example.auth.service.AuthService;

import reactor.core.publisher.Mono;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    ReloadableResourceBundleMessageSource messageSource;

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> pingpong(ServerHttpRequest request) {
        logger.info("=== === === Someone has Entered AdminController. {}", request.getHeaders());
        String responseBody = "Ha!! Ha!! Ha!!";
        return Mono.just(ResponseEntity.ok() //
                .header("custom-header2", "Added in adminController") //
                .body(responseBody)) //
                .onErrorMap(e -> new GlobalException("951", e.getMessage()));
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LoginResponse>> login(ServerHttpRequest request,
            @RequestBody LoginRequest loginRequest) throws GlobalException, NoSuchAlgorithmException {
        LoginResponse loginResponse = authService.login(loginRequest);
        logger.info("{}", loginResponse);
        return Mono.just(ResponseEntity.ok() //
                .body(loginResponse)) //
                .onErrorMap(e -> new GlobalException("951", e.getMessage()));
    }

}
