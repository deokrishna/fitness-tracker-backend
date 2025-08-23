package com.fitnessapp.fitnesstracker.repository;

import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout,Long> {
    List<Workout> findByUser(User user);
}
