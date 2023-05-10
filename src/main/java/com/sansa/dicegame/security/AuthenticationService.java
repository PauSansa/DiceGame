package com.sansa.dicegame.security;

import com.sansa.dicegame.payloads.AuthResponse;
import com.sansa.dicegame.payloads.LoginRequest;
import com.sansa.dicegame.payloads.RegisterRequest;
import com.sansa.dicegame.security.jwt.JwtService;
import com.sansa.dicegame.user.Role;
import com.sansa.dicegame.user.User;
import com.sansa.dicegame.user.UserRepository;
import com.sansa.dicegame.user.UserService;
import com.sansa.dicegame.user.dto.UserToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserToDto userToDto;

    public User createUser(RegisterRequest request){

        User user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(new Date())
                .role(Role.PLAYER)
                .build();

        userRepository.saveAndFlush(user);
        user.setUsername();

        return userRepository.save(user);
    }

    public AuthResponse authenthicate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user, new HashMap<>());
        return AuthResponse.builder()
                .user(userToDto.apply(user))
                .token(token)
                .build();

    }
}
