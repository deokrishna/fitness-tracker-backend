package com.fitnessapp.fitnesstracker.repository;

import com.fitnessapp.fitnesstracker.model.Cardio;
import com.fitnessapp.fitnesstracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardioRepository extends JpaRepository<Cardio, Long> {
    List<Cardio> findByUser(User user);
}
