package com.fitnessapp.fitnesstracker.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkoutResponseDTO {
    private Long id;
    private String exerciseName;
    private int sets;
    private int reps;
    private double weight;
    private int durationMinutes;
    private int caloriesBurned;
    private LocalDateTime date;
}
