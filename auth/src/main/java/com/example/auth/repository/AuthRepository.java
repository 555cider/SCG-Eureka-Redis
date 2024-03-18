package com.example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.model.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    Auth findByEmailAndPassword(String email, String password);

}
