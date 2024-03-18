package com.example.auth.service;

import com.example.auth.common.GlobalException;
import com.example.auth.model.Auth;
import com.example.auth.model.LoginRequest;
import com.example.auth.model.LoginResponse;
import com.example.auth.model.Token;
import com.example.auth.repository.AuthRepository;
import com.example.auth.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final AuthRepository authRepository;
    private final TokenRepository tokenRepository;

    public AuthService(AuthRepository authRepository, TokenRepository tokenRepository) {
        this.authRepository = authRepository;
        this.tokenRepository = tokenRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) throws GlobalException, NoSuchAlgorithmException {
        logger.info("{}", loginRequest);

        List<Auth> authList = authRepository.findAll();
        logger.info("authList = {}", authList);

        Auth auth = authRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        logger.info("auth = {}", auth);
        if (auth == null) {
            throw new GlobalException("952", "아이디와 비밀번호가 일치하지 않습니다.");
        }

        Token token = tokenRepository.save(new Token(auth));
        logger.info("new token = {}", token);

        return new LoginResponse(token.getId());
    }

}
