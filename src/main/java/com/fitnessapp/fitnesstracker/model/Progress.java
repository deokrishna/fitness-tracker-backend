package com.fitnessapp.fitnesstracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date = LocalDate.now();
    private double weight;
    private double bodyFat;
    private double muscleMass;
    @Column(nullable = true)
    private double sleepHours;  // sleep duration (in hours)
    @Column(nullable = true)
    private int waterIntake;    // water intake (ml/day)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
