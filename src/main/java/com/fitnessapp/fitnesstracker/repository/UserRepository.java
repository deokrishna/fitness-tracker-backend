package com.fitnessapp.fitnesstracker.repository;

import com.fitnessapp.fitnesstracker.model.NutritionLog;
import com.fitnessapp.fitnesstracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);

    interface NutritionRepository extends JpaRepository<NutritionRepository,Long> {
        List<NutritionLog> findByUser(User user);
    }
}
