package com.mycompany.auth;

import com.mycompany.auth.models.CustomUserDetails;
import com.mycompany.auth.requests.RefreshRequest;
import com.mycompany.auth.requests.UserCreateRequest;
import com.mycompany.auth.requests.UserLoginRequest;
import com.mycompany.auth.responses.RefreshResponse;
import com.mycompany.auth.responses.UserLoginResponse;
import com.mycompany.database.models.LeisuUser;
import com.mycompany.database.models.TokenSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCreateRequest request) {
        if (authService.existsUsername(request.getUsername())) {
            return new ResponseEntity<>("username already exists", HttpStatus.BAD_REQUEST);
        }
        authService.registerUser(request);
        return ResponseEntity.ok("user registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        Authentication authenticate = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (!authenticate.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        CustomUserDetails user = (CustomUserDetails) authService.loadUserByUsername(request.getUsername());
        String refreshToken = JwtUtils.generateRefreshToken();
        TokenSession session = authService.createSession(user.getUser(), refreshToken);
        String token = jwtUtils.generateToken(user, session);
        return ResponseEntity.ok(new UserLoginResponse(token, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(@RequestBody RefreshRequest request) {
        String token = request.getRefreshToken();

        TokenSession session = authService.getSessionByToken(token);
        if (!authService.isSessionValid(session)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        LeisuUser leisuUser = authService.getLeisuUserById(session.getUserId());
        if (null == leisuUser) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String newRefreshToken = authService.rotateToken(session);
        String newAccessToken = jwtUtils.generateToken(new CustomUserDetails(leisuUser), session);
        return ResponseEntity.ok(new RefreshResponse(newAccessToken, newRefreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        String accessToken = token.substring(7);
        String username = jwtUtils.extractUsername(accessToken);
        if (!authService.existsUsername(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String sessionId = jwtUtils.getSessionId(accessToken);
        TokenSession session = authService.getSessionById(sessionId);
        if(!authService.isSessionValid(session)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        authService.revokeSession(session);
        return ResponseEntity.ok("successfully logged out");
    }
}