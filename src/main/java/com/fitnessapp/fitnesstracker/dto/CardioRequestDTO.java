package com.fitnessapp.fitnesstracker.dto;

import lombok.Data;

@Data
public class CardioRequestDTO {
    private String activityType;
    private double distanceKm;
    private int durationMinutes;
}
