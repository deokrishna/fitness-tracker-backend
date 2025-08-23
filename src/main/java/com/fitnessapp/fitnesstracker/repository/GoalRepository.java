package com.fitnessapp.fitnesstracker.repository;

import com.fitnessapp.fitnesstracker.model.Goal;
import com.fitnessapp.fitnesstracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUser(User user);
}
