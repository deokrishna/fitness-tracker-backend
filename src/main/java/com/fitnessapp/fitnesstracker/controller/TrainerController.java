package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.model.Trainer;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.repository.TrainerRepository;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a trainer
    @PostMapping
    public Trainer createTrainer(@RequestBody Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    // Get all trainers
    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    // Assign a user to a trainer
    @PutMapping("/{trainerId}/assign/{userId}")
    public User assignUserToTrainer(@PathVariable Long trainerId, @PathVariable Long userId) {
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setTrainer(trainer);
        return userRepository.save(user);
    }

    // Get all users assigned to a trainer
    @GetMapping("/{trainerId}/users")
    public List<User> getUsersByTrainer(@PathVariable Long trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        return trainer.getUsers();
    }
}
