package com.example.auth.model;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.UUID;

@DynamicInsert
@Entity
@Table(name = "token", schema = "temp")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "auth_id")
    private Auth auth;

    @Column
    private UUID token;

    @Column
    private LocalDateTime issuedAt;

    public Token(Auth auth) {
        this.auth = auth;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Token{" + "id=" + id + ", auth=" + auth + ", token=" + token + ", issuedAt=" + issuedAt + '}';
    }

}
