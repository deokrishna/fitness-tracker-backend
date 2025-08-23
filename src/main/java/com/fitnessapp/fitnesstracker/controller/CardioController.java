package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.model.Cardio;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.repository.CardioRepository;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardio")
public class CardioController {

    @Autowired
    private CardioRepository cardioRepository;

    @Autowired
    private UserRepository userRepository;

    // Log cardio session
    @PostMapping("/{userId}")
    public Cardio logCardio(@PathVariable Long userId, @RequestBody Cardio cardio) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        cardio.setUser(user);
        return cardioRepository.save(cardio);
    }

    // Get all cardio sessions for user
    @GetMapping("/{userId}")
    public List<Cardio> getCardioByUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cardioRepository.findByUser(user);
    }
}
