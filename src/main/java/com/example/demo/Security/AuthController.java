package com.example.demo.Security;

import com.example.demo.USER.AppUser;
import com.example.demo.USER.AppUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserRepository userRepository;

    public AuthController(
            AuthenticationManager authenticationManager,
            AppUserRepository userRepository) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        AppUser user =
                userRepository
                        .findByUsername(
                                request.getUsername())
                        .orElseThrow();

        return JwtUtil.generateToken(
                user.getUsername(),
                user.getRole());
    }
}