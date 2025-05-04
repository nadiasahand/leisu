package com.mycompany.auth;

import com.mycompany.database.models.TokenSession;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {
    private final static String CLAIM_KEY_ROLES = "roles";
    private final static String CLAIM_KEY_SESSION = "session";
    private final static String CLAIM_KEY_USER_AGENT = "user-agent";
    private final static Long ACCESS_TOKEN_EXPIRATION = 24 * 60 * 60 * 1000L;
    private final JwtProperties jwtProperties;

    public JwtUtils(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(UserDetails userDetails, TokenSession session) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .claim(CLAIM_KEY_ROLES, userDetails.getAuthorities())
                .claim(CLAIM_KEY_SESSION, session.getSessionId())
                .claim(CLAIM_KEY_USER_AGENT, session.getUserAgent())
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8))).compact();
    }

    public static String generateRefreshToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[64];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token).getPayload();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) && getClaims(token).getExpiration().after(new Date(System.currentTimeMillis()));
    }

    public String getSessionId(String token) {
        return getClaims(token).get(CLAIM_KEY_SESSION).toString();
    }
}
