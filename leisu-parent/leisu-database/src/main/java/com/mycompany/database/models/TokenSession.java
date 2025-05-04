package com.mycompany.database.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TokenSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sessionId;
    private Long expirationTime;
    private Long issuedTime;
    private String userAgent;
    private Boolean revoked;
    private Long userId;
    private String refreshToken;

    public TokenSession(String sessionId, Long expirationTime, Long issuedTime, Long userId, String refreshToken, Boolean revoked, String userAgent) {
        this.expirationTime = expirationTime;
        this.issuedTime = issuedTime;
        this.userAgent = userAgent;
        this.revoked = revoked;
        this.userId = userId;
        this.sessionId = sessionId;
        this.refreshToken = refreshToken;
    }

    public TokenSession() {

    }
}
