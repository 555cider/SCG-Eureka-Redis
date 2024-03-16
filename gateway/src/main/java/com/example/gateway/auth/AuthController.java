package com.example.gateway.auth;

import com.example.gateway.common.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Locale;

@RestController
public class AuthController {

    private final AuthService authService;
    @Autowired
    MessageSource messageSource;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/auth")
    public Mono<ResponseEntity<Auth>> getAuth(@RequestParam("id") String hashKey) {
        return authService.get(hashKey) //
                .map(ResponseEntity::ok) //
                .onErrorMap(e -> {
                    if (e instanceof GlobalException) {
                        return new GlobalException("951", messageSource.getMessage("error.951", null, Locale.KOREAN));
                    }
                    return new GlobalException("951", e.getMessage());
                });
    }

    @PostMapping("/auth")
    public Mono<ResponseEntity<Long>> postAuth(@RequestBody Auth auth) {
        return this.authService.post(auth) //
                .map(ResponseEntity::ok) //
                .onErrorMap(e -> {
                    if (e instanceof GlobalException) {
                        return new GlobalException("951", messageSource.getMessage("error.951", null, Locale.KOREAN));
                    }
                    return new GlobalException("951", e.getMessage());
                });
    }

    @DeleteMapping("/auth")
    public Mono<ResponseEntity<Boolean>> deleteAuth(@RequestBody Auth auth) {
        return this.authService.delete(auth) //
                .map(ResponseEntity::ok) //
                .onErrorMap(e -> {
                    if (e instanceof GlobalException) {
                        return new GlobalException("951", messageSource.getMessage("error.951", null, Locale.KOREAN));
                    }
                    return new GlobalException("951", e.getMessage());
                });
    }

    @GetMapping("/hello")
    public String get() {
        System.out.println("hello");
        return "Hello World";
    }

}