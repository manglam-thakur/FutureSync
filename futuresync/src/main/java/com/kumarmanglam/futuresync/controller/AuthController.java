package com.kumarmanglam.futuresync.controller;

import com.kumarmanglam.futuresync.dto.AuthRequest;
import com.kumarmanglam.futuresync.dto.AuthResponse;
import com.kumarmanglam.futuresync.dto.RegisterRequest;

import com.kumarmanglam.futuresync.model.User;

import com.kumarmanglam.futuresync.repository.UserRepository;

import com.kumarmanglam.futuresync.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ REGISTER

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ) {

        User existingUser =
                userRepository.findByEmail(
                        request.getEmail()
                );

        if (existingUser != null) {

            return "User already exists";

        }

        User user = new User();

        user.setName(
                request.getName()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        userRepository.save(user);

        return "Registration successful";
    }

    // ✅ LOGIN

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody AuthRequest request
    ) {

        User user =
                userRepository.findByEmail(
                        request.getEmail()
                );

        if (
                user == null ||

                        !passwordEncoder.matches(
                                request.getPassword(),
                                user.getPassword()
                        )
        ) {

            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        String token =

                jwtUtil.generateToken(
                        user.getEmail(),
                        user.getId()
                );

        return new AuthResponse(

                token,

                user.getId(),

                user.getName(),

                user.getEmail()
        );
    }
}