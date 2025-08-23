package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.model.NutritionLog;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.repository.NutritionLogRepository;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutrition")
public class NutritionLogController {
    @Autowired
    private NutritionLogRepository nutritionLogRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{userId}")
    public NutritionLog createNutrionLog(@PathVariable Long userId, @RequestBody NutritionLog nutritionLog){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        nutritionLog.setUser(user);
        return nutritionLogRepository.save(nutritionLog);
    }
    @GetMapping("/{userId}")
    public List<NutritionLog> getNutritionLogsByUser(@PathVariable Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        return  nutritionLogRepository.findByUser(user);
    }
}
