package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.model.Workout;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import com.fitnessapp.fitnesstracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/{userId}")
    public Workout createWorkout(@PathVariable Long userId, @RequestBody Workout workout){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        workout.setUser(user);
        return workoutRepository.save(workout);
    }

    @GetMapping("/{userId}")
    public List<Workout> getWorkoutsByUser(@PathVariable Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        return workoutRepository.findByUser(user);
    }
}
