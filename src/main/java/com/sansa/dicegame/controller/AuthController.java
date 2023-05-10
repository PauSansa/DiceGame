package com.sansa.dicegame.controller;

import com.sansa.dicegame.exception.EmptyPasswordException;
import com.sansa.dicegame.exception.UsernameAlreadyTakenException;
import com.sansa.dicegame.payloads.AuthResponse;
import com.sansa.dicegame.payloads.LoginRequest;
import com.sansa.dicegame.payloads.RegisterRequest;
import com.sansa.dicegame.security.AuthenticationService;
import com.sansa.dicegame.security.jwt.JwtService;
import com.sansa.dicegame.user.User;
import com.sansa.dicegame.user.UserService;
import com.sansa.dicegame.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> newPlayer(@RequestBody RegisterRequest request) throws UsernameAlreadyTakenException, EmptyPasswordException {
        User user = authService.createUser(cleanRegisterRequest(request));
        String token = jwtService.generateToken(user, new HashMap<>());
        UserDto userDto = userService.userToDTO(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .user(userDto)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.authenthicate(request));
    }






    private RegisterRequest cleanRegisterRequest(RegisterRequest request) throws UsernameAlreadyTakenException, EmptyPasswordException {
        if(userService.exists(request.getName())){
            throw new UsernameAlreadyTakenException("This Username is Already Taken");
        }

        if(request.getPassword() == null || request.getPassword().isEmpty()){
            throw new EmptyPasswordException("The password cannot be empty");
        }

        if(request.getName() == null || request.getName().isEmpty()){
            request.setName("ANÓNIM");
        }
        return request;

    }

}
