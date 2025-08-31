package com.fitnessapp.fitnesstracker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CardioResponseDTO {
    private Long id;
    private String activityType;
    private double distanceKm;
    private int durationMinutes;
    private double pace;
    private int caloriesBurned;
    private LocalDateTime date;
}
