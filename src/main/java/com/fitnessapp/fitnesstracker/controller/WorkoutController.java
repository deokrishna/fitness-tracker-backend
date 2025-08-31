package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.dto.WorkoutDTO;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.model.Workout;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import com.fitnessapp.fitnesstracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping
    public Workout createWorkout(@RequestBody WorkoutDTO workoutDTO, @AuthenticationPrincipal UserDetails userDetails){
        User user= userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()->new RuntimeException("User not found"));
        Workout workout=new Workout();
        workout.setExerciseName(workoutDTO.getExerciseName());
        workout.setSets(workoutDTO.getSets());
        workout.setReps(workoutDTO.getReps());
        workout.setWeight(workoutDTO.getWeight());
        workout.setDurationMinutes(workoutDTO.getDurationMinutes());
        workout.setCaloriesBurned(workoutDTO.getCaloriesBurned());
        workout.setUser(user);
        return workoutRepository.save(workout);
    }

    @GetMapping("/me")
    public List<Workout> getWorkoutsByUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user=userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()->new RuntimeException("User not found"));
        return workoutRepository.findByUser(user);
    }
}
