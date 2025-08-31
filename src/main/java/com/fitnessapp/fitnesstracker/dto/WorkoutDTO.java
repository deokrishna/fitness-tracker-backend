package com.fitnessapp.fitnesstracker.dto;
import lombok.Data;
@Data
public class WorkoutDTO {
    private String exerciseName;
    private int sets;
    private int reps;
    private double weight;
    private int durationMinutes;
    private int caloriesBurned;
}
