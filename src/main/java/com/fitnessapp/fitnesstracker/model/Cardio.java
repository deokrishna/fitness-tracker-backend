package com.fitnessapp.fitnesstracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cardio")
public class Cardio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String activityType;   // Running, Cycling, Rowing, etc.
    private double distanceKm;     // distance in km
    private int durationMinutes;   // workout duration
    private double pace;           // min/km or km/h
    private int caloriesBurned;

    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
