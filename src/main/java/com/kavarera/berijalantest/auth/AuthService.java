package com.kavarera.berijalantest.auth;


import com.kavarera.berijalantest.config.JwtService;
import com.kavarera.berijalantest.user.User;
import com.kavarera.berijalantest.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullname(request.getFullname())
                .build();

        repo.save(user);
        var token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();

    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = repo.findByUsername(request.getUsername()).orElseThrow();

        return AuthResponse.builder().token(jwtService.generateToken(user)).build();
    }
}
