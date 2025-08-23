package com.fitnessapp.fitnesstracker.repository;

import com.fitnessapp.fitnesstracker.model.NutritionLog;
import com.fitnessapp.fitnesstracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NutritionLogRepository extends JpaRepository<NutritionLog,Long> {
    List<NutritionLog> findByUser(User user);
}
