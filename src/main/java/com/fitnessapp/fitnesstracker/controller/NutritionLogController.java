package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.dto.NutritionLogRequestDTO;
import com.fitnessapp.fitnesstracker.dto.NutritionLogResponseDTO;
import com.fitnessapp.fitnesstracker.model.NutritionLog;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.repository.NutritionLogRepository;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nutrition")
public class NutritionLogController {
    @Autowired
    private NutritionLogRepository nutritionLogRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public NutritionLogResponseDTO createNutrionLog(@AuthenticationPrincipal UserDetails userDetails, @RequestBody NutritionLogRequestDTO dto){
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        NutritionLog log = new NutritionLog();
        log.setMealType(dto.getMealType());
        log.setFoodType(dto.getFoodType());
        log.setCalories(dto.getCalories());
        log.setProtein(dto.getProtein());
        log.setCarbs(dto.getCarbs());
        log.setFats(dto.getFats());
        log.setUser(user);

        NutritionLog saved = nutritionLogRepository.save(log);

        NutritionLogResponseDTO res = new NutritionLogResponseDTO();
        res.setId(saved.getId());
        res.setMealType(saved.getMealType());
        res.setFoodType(saved.getFoodType());
        res.setCalories(saved.getCalories());
        res.setProtein(saved.getProtein());
        res.setCarbs(saved.getCarbs());
        res.setFats(saved.getFats());
        res.setDate(saved.getDate());

        return res;
    }
    @GetMapping("/me")
    public List<NutritionLogResponseDTO> getMyNutritionLogs(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return nutritionLogRepository.findByUser(user).stream().map(log -> {
            NutritionLogResponseDTO dto = new NutritionLogResponseDTO();
            dto.setId(log.getId());
            dto.setMealType(log.getMealType());
            dto.setFoodType(log.getFoodType());
            dto.setCalories(log.getCalories());
            dto.setProtein(log.getProtein());
            dto.setCarbs(log.getCarbs());
            dto.setFats(log.getFats());
            dto.setDate(log.getDate());
            return dto;
        }).collect(Collectors.toList());
    }
}
