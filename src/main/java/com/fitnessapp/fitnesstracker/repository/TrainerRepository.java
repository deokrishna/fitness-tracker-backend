package com.fitnessapp.fitnesstracker.repository;

import com.fitnessapp.fitnesstracker.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
