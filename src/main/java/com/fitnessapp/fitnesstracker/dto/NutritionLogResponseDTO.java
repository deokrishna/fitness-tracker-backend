package com.fitnessapp.fitnesstracker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NutritionLogResponseDTO {
    private Long id;
    private String mealType;
    private String foodType;
    private int calories;
    private double protein;
    private double carbs;
    private double fats;
    private LocalDateTime date;
}
