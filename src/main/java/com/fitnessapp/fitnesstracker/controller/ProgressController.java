package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.model.Progress;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.repository.ProgressRepository;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {
    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/{userId}")
    public Progress logProgress(@PathVariable Long userId, @RequestBody Progress progress){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        progress.setUser(user);
        return progressRepository.save(progress);
    }

    @GetMapping("/{userId}")
    public List<Progress> getProgressByUser(@PathVariable Long userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        return progressRepository.findByUser(user);
    }

}
