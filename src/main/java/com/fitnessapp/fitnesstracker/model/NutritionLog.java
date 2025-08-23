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
@Table(name = "nutrition_logs")
public class NutritionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mealType;
    private String foodType;
    private int calories;
    private double protein;
    private double carbs;
    private double fats;
    private LocalDateTime date=LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
