package com.fitnessapp.fitnesstracker.dto;

import lombok.Data;

@Data
public class NutritionLogRequestDTO {
    private String mealType;
    private String foodType;
    private int calories;
    private double protein;
    private double carbs;
    private double fats;
}
