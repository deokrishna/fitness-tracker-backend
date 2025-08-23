package com.fitnessapp.fitnesstracker.repository;

import com.fitnessapp.fitnesstracker.model.Progress;
import com.fitnessapp.fitnesstracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByUser(User user);
}
