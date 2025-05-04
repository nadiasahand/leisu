package com.mycompany.auth;

import com.mycompany.auth.models.CustomUserDetails;
import com.mycompany.auth.requests.UserCreateRequest;
import com.mycompany.database.enums.UserRole;
import com.mycompany.database.models.LeisuUser;
import com.mycompany.database.models.TokenSession;
import com.mycompany.database.repositories.TokenSessionRepository;
import com.mycompany.database.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    private final static Long REFRESH_TOKEN_EXPIRATION = 15 * 60 * 1000L;

    private final UserRepository repository;
    private final TokenSessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repository, TokenSessionRepository sessionRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LeisuUser user = repository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public Boolean existsUsername(String username) {
        return repository.existsUsername(username);
    }

    public void save(LeisuUser leisuUser) {
        repository.save(leisuUser);
    }

    public LeisuUser toLeisuUser(UserCreateRequest request) {
        LeisuUser user = new LeisuUser();
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAge(request.getAge());
        user.setUserName(request.getUsername());
        user.setRole(UserRole.USER);
        return user;
    }

    public void registerUser(UserCreateRequest request) {
        LeisuUser user = this.toLeisuUser(request);
        this.save(user);
    }

    public LeisuUser getLeisuUserById(long id) {
        return repository.getById(id);
    }

    public TokenSession createSession(LeisuUser user, String refreshToken) {
        String uuid = UUID.randomUUID().toString();
        TokenSession tokenSession = new TokenSession(
                uuid,
                System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION,
                System.currentTimeMillis(),
                user.getId(),
                refreshToken,
                Boolean.FALSE,
                "user-agent"
        );
        return sessionRepository.save(tokenSession);
    }

    public Boolean isSessionValid(String sessionId, LeisuUser user) {
        TokenSession session = sessionRepository.getBySessionId(sessionId);
        return null != session
                && Boolean.FALSE == session.getRevoked()
                && session.getExpirationTime() > System.currentTimeMillis()
                && Objects.equals(session.getUserId(), user.getId());
    }

    public Boolean isSessionValid(TokenSession session) {
        return null != session
                && Boolean.FALSE == session.getRevoked()
                && session.getExpirationTime() > System.currentTimeMillis();
    }

    public TokenSession getSessionByToken(String refreshToken) {
        return sessionRepository.getByToken(refreshToken);
    }

    public TokenSession getSessionById(String sessionId) {
        return sessionRepository.getBySessionId(sessionId);
    }

    public String rotateToken(TokenSession session) {
        String refreshToken = JwtUtils.generateRefreshToken();
        session.setRefreshToken(refreshToken);
        session.setExpirationTime(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION);
        sessionRepository.update(session);
        return refreshToken;
    }

    public void revokeSession(TokenSession session) {
        session.setRevoked(Boolean.TRUE);
        sessionRepository.update(session);
    }
}
