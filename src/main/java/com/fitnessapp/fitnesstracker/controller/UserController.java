package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.dto.UserDto;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }
    @GetMapping
    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(),user.getEmail()))
                .collect(Collectors.toList());
    }
    @GetMapping("/me")
    public UserDto getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        User user= userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow(()->new RuntimeException("User not found"));
        return new UserDto(user.getId(),user.getEmail());
    }
}
