package com.fitnessapp.fitnesstracker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String specialization; // e.g. Strength, Cardio, Nutrition

    // One trainer can have many users
    @OneToMany(mappedBy = "trainer")
    @JsonManagedReference
    private List<User> users;
}
