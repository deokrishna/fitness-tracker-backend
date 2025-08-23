package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
