package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.config.JwtUtil;
import com.fitnessapp.fitnesstracker.model.Role;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public User signup(@RequestBody User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole()==null){
            user.setRole(Role.USER);
        }
        return userRepository.save(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        return jwtUtil.generateToken(user.getEmail());
    }
}

